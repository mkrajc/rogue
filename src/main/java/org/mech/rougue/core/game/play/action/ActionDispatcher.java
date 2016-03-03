package org.mech.rougue.core.game.play.action;

import java.util.List;
import org.mech.rougue.core.engine.handler.input.InputEvent;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.factory.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActionDispatcher {

	private static final Logger LOG = LoggerFactory.getLogger(ActionDispatcher.class);

	@Inject
	private List<Action> actions;

	@Inject
	private ActionMapping mapping;

	public void invokeAction(InputEvent event, GameContext context) {
		final String actionType = mapping.getActionType(event);

		if (actionType == null) {
			LOG.debug("No actionType bind to key [" + event + "]");
			return;
		}

		Action action = null;
		for (Action a : actions) {
			if (a.supports(actionType)) {
				action = a;
				break;
			}
		}

		if (action != null) {
			action.invoke(context);
		}

		if (action == null) {
			LOG.warn("No action bind to actionType [" + actionType + "]");
		}

	}

	public void addAction(Action action) {
		actions.add(action);
	}

	public void removeAction(Action action) {
		actions.remove(action);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " {actions=" + actions.size() + "}";
	}

}
