package org.mech.rougue.core.game;

import java.util.ArrayList;
import java.util.List;
import org.mech.rougue.core.game.play.action.ActionDispatcher;
import org.mech.rougue.core.game.state.GameState;
import org.mech.rougue.core.r.context.ContextAwareGObject;
import org.mech.rougue.core.r.event.Event;
import org.mech.rougue.core.r.event.EventBus;
import org.mech.rougue.core.r.handler.game.UpdateAwareGObject;
import org.mech.rougue.core.r.model.common.GObject;
import org.mech.rougue.core.r.object.GObjectUtils;
import org.mech.rougue.factory.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameContext {

	private static final Logger LOG = LoggerFactory.getLogger(GameContext.class);

	@Inject
	public GameState state;
	
	@Inject
	public ActionDispatcher actionDispatcher;

	public final EventBus eventBus = new EventBus(this);

	private final List<GObject> gameObjects = new ArrayList<GObject>();

	private GameData data = new GameData();

	public GameData getData() {
		return data;
	}

	public void setData(final GameData data) {
		this.data = data;
	}

	public GameState getState() {
		return state;
	}

	public void setState(final GameState state) {
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

	public void reset() {
		this.data.reset();
	}

	public void add(final GObject gObject) {
		gameObjects.add(gObject);
		if (gObject instanceof ContextAwareGObject) {
			((ContextAwareGObject) gObject).onAdd(this);
		}
		if (gObject instanceof Event.Handler) {
			((Event.Handler) gObject).registerHandlers(eventBus);
		}
	}

	public void remove(final GObject gObject) {
		gameObjects.remove(gObject);
		if (gObject instanceof ContextAwareGObject) {
			((ContextAwareGObject) gObject).onRemove(this);
		}
		if (gObject instanceof Event.Handler) {
			eventBus.removeHandler((Event.Handler) gObject);
		}
	}

	public <T> List<T> getGameObjects(final Class<T> clazz) {
		return GObjectUtils.getObjectsOfType(gameObjects, clazz);
	}
	
	public <T> T getGameObject(final Class<T> clazz) {
		return GObjectUtils.getObjectOfType(gameObjects, clazz);
	}

}
