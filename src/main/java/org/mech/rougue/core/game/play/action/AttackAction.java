package org.mech.rougue.core.game.play.action;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.factory.Inject;

public class AttackAction extends DefaultAction {

	@Inject
	private GameContext context;

	@Override
	protected void doInvoke() {
		System.out.println("TODO action ");
	}

	@Override
	public String getActionType() {
		return "action_attack";
	}

}
