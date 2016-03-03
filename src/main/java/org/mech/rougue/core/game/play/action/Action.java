package org.mech.rougue.core.game.play.action;

import org.mech.rougue.core.game.GameContext;

public abstract class Action {

	protected abstract void invoke(GameContext context);

	protected boolean supports(String type) {
		return getActionType().equals(type);
	}

	public abstract String getActionType();
}
