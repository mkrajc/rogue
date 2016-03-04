package org.mech.rougue.core.game;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;

import org.mech.rogue.game.context.State;
import org.mech.rogue.game.render.map.RenderObject;
import org.mech.rougue.core.game.model.map.render.SeenMapRenderer;
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

    private final List<GObject> gameObjects = new ArrayList<GObject>();
    private final List<RenderObject> renderObjects = new ArrayList<RenderObject>();
    private final List<UpdateAwareGObject> updateAwareObjects = new ArrayList<UpdateAwareGObject>();

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
        System.out.println("creating context");
        getState().switchState(SeenMapRenderer.SEE_ALL_SWITCH);
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
            }
            if (gObject instanceof UpdateAwareGObject) {
                updateAwareObjects.add((UpdateAwareGObject) gObject);
            }
        }
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
            }
            if (gObject instanceof UpdateAwareGObject) {
                updateAwareObjects.remove((UpdateAwareGObject) gObject);
            }
        }
    }

    private <T> List<T> getGameObjects(final Class<T> clazz) {
        return GObjectUtils.getObjectsOfType(gameObjects, clazz);
    }

    public LightMask getLightMask() {
        return lightMask;
    }

    public Player getPlayer() {
        return GObjectUtils.getObjectOfType(gameObjects, Player.class);
    }

    public static long rtime = 0;
    public static long ltime = 0;

    public List<UpdateAwareGObject> getUpdateAwareObjects() {
        return updateAwareObjects;
    }


    public List<RenderObject> getRenderObjects(Position pos) {
        long start = System.currentTimeMillis();
        //TODO better finding
        final ArrayList<RenderObject> filteredRenderObjects = new ArrayList<>();
        for (Iterator<RenderObject> it = renderObjects.iterator(); it.hasNext(); ) {
            RenderObject obj = it.next();
            if (obj.getPosition().equals(pos)) {
                filteredRenderObjects.add(obj);
            }
        }
        rtime += (System.currentTimeMillis() - start);
        return filteredRenderObjects;
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
