package org.mech.rougue.core.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.mech.rogue.game.context.State;
import org.mech.rogue.game.render.map.RenderObject;
import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.game.play.action.ActionDispatcher;
import org.mech.rougue.core.r.context.ContextAwareGObject;
import org.mech.rougue.core.r.event.Event;
import org.mech.rougue.core.r.event.EventBus;
import org.mech.rougue.core.r.handler.game.UpdateAwareGObject;
import org.mech.rougue.core.r.handler.game.light.LightMask;
import org.mech.rougue.core.r.model.common.GObject;
import org.mech.rougue.core.r.object.GObjectUtils;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.geometry.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameContext {

    private static final Logger LOG = LoggerFactory.getLogger(GameContext.class);

    @Inject
    public State state;

    @Inject
    public ActionDispatcher actionDispatcher;

    public final EventBus eventBus = new EventBus(this);
    public GameData data = new GameData();

    private LightMask lightMask;

    private final List<GObject> gameObjects = new ArrayList<GObject>();

    public GameData getData() {
        return data;
    }

    public State getState() {
        return state;
    }

    public void setState(final State state) {
        this.state = state;
    }

    public void update() {
        eventBus.startUpdate();
        if (!getState().isPaused()) {
            final List<UpdateAwareGObject> updateHandlers = getGameObjects(UpdateAwareGObject.class);
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
        }
    }

    public <T> List<T> getGameObjects(final Class<T> clazz) {
        return GObjectUtils.getObjectsOfType(gameObjects, clazz);
    }

    public LightMask getLightMask() {
        return lightMask;
    }

    public Player getPlayer() {
        return GObjectUtils.getObjectOfType(gameObjects, Player.class);
    }

    public List<RenderObject> getRenderObjects() {
        return GObjectUtils.getObjectsOfType(gameObjects, RenderObject.class);
    }

    public List<RenderObject> getRenderObjects(Position pos) {
        //TODO better finding
        List<RenderObject> renderObjects = GObjectUtils.getObjectsOfType(gameObjects, RenderObject.class);
        for (Iterator<RenderObject> it = renderObjects.iterator();it.hasNext();){
            if(!it.next().getPosition().equals(pos)){
                it.remove();
            }
        }
        return renderObjects;
    }


    public void setLightMask(LightMask lightMask) {
        add(lightMask);
        this.lightMask = lightMask;
    }

}
