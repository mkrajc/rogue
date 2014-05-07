package org.mech.rougue.core.game.play.action;

public abstract class Action {

	protected abstract void invoke();

	protected boolean supports(String type) {
		return getActionType().equals(type);
	}

	public abstract String getActionType();
}
