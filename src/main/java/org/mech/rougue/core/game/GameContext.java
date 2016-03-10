package org.mech.rougue.core.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;

import org.mech.rogue.game.context.State;
import org.mech.rogue.game.render.map.RenderObject;
import org.mech.rogue.game.render.map.SeenMapRenderer$;
import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.r.context.ContextAwareGObject;
import org.mech.rougue.core.r.event.Event;
import org.mech.rougue.core.r.event.EventBus;
import org.mech.rougue.core.r.handler.game.UpdateAwareGObject;
import org.mech.rougue.core.r.handler.game.light.LightMask;
import org.mech.rougue.core.r.model.common.GObject;
import org.mech.rougue.core.r.model.light.LightSource;
import org.mech.rougue.core.r.object.GObjectUtils;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.geometry.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameContext {

    private static final Logger LOG = LoggerFactory.getLogger(GameContext.class);

    @Inject
    public State state;

    public final EventBus eventBus = new EventBus(this);
    public GameData data = new GameData();

    private LightMask lightMask;

    private final List<GObject> gameObjects = new ArrayList<>();
    private final List<RenderObject> renderObjects = new ArrayList<>();
    private final List<UpdateAwareGObject> updateAwareObjects = new ArrayList<>();

    public GameData getData() {
        return data;
    }

    public State getState() {
        return state;
    }

    public void setState(final State state) {
        this.state = state;
    }

    @PostConstruct
    public void after() {
        getState().switchState(SeenMapRenderer$.MODULE$.SEE_ALL_SWITCH());
    }

    public void update() {
        eventBus.startUpdate();
        if (!getState().isPaused()) {
            final List<UpdateAwareGObject> updateHandlers = getUpdateAwareObjects();
            for (final UpdateAwareGObject update : updateHandlers) {
                update.update(this);
            }
        }
        eventBus.stopUpdate();
    }

    public void add(final GObject gObject) {
        if (gObject != null) {
            gameObjects.add(gObject);
            register(gObject);
        }
    }

    private void register(final GObject gObject) {
        if (gObject != null) {
            if (gObject instanceof ContextAwareGObject) {
                ((ContextAwareGObject) gObject).onAdd(this);
            }
            if (gObject instanceof Event.Handler) {
                ((Event.Handler) gObject).registerHandlers(eventBus);
            }
            if (gObject instanceof RenderObject) {
                renderObjects.add((RenderObject) gObject);
                sortRenderObjects();
            }
            if (gObject instanceof UpdateAwareGObject) {
                updateAwareObjects.add((UpdateAwareGObject) gObject);
            }
        }
    }

    private void sortRenderObjects() {
        Collections.sort(renderObjects, new Comparator<RenderObject>() {
            @Override
            public int compare(RenderObject o1, RenderObject o2) {
                return o1.getPosition().compareTo(o2.getPosition());
            }
        });
    }

    public List<RenderObject> getRenderObjects(Position position) {
        int from = binary_search_starting_point(renderObjects, position, 0, renderObjects.size());
        if (from < 0) {
            return null;
        } else {
            List<RenderObject> ro = new ArrayList<>(3);
            for (int i = from; i < renderObjects.size() && renderObjects.get(i).getPosition().equals(position); i++) {
                ro.add(renderObjects.get(i));
            }
            return ro;
        }

    }

    private int binary_search_starting_point(List<RenderObject> arr, Position position, int imin, int imax) {
        // continue searching while [imin,imax] is not empty
        while (imin <= imax) {
            // calculate the midpoint for roughly equal partition
            int imid = (imin + imax) / 2;
            int cmp = arr.get(imid).getPosition().compareTo(position);
            if (cmp == 0)
                // key found at index imid
                return imid;
                // determine which subarray to search
            else if (cmp < 0)
                // change min index to search upper subarray
                imin = imid + 1;
            else
                // change max index to search lower subarray
                imax = imid - 1;
        }
        // key was not found
        return -1;
    }

    public void remove(final GObject gObject) {
        if (gObject != null) {
            gameObjects.remove(gObject);
            if (gObject instanceof ContextAwareGObject) {
                ((ContextAwareGObject) gObject).onRemove(this);
            }
            if (gObject instanceof Event.Handler) {
                eventBus.removeHandler((Event.Handler) gObject);
            }
            if (gObject instanceof RenderObject) {
                renderObjects.remove((RenderObject) gObject);
                sortRenderObjects();
            }
            if (gObject instanceof UpdateAwareGObject) {
                updateAwareObjects.remove((UpdateAwareGObject) gObject);
            }
        }
    }

    public LightMask getLightMask() {
        return lightMask;
    }

    public Player getPlayer() {
        return GObjectUtils.getObjectOfType(gameObjects, Player.class);
    }

    public static long ltime = 0;

    public List<UpdateAwareGObject> getUpdateAwareObjects() {
        return updateAwareObjects;
    }

    public List<RenderObject> getRenderObjects() {
        return renderObjects;
    }


    public void setLightMask(LightMask lightMask) {
        add(lightMask);
        this.lightMask = lightMask;
    }

    public List<LightSource> getLightSources() {
        long start = System.currentTimeMillis();
        List<LightSource> objectsOfType = GObjectUtils.getObjectsOfType(gameObjects, LightSource.class);
        ltime += (System.currentTimeMillis() - start);
        return objectsOfType;
    }
}
