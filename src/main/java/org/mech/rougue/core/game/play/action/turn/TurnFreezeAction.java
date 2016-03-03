package org.mech.rougue.core.game.play.action.turn;

import org.mech.rogue.game.context.State;
import org.mech.rougue.core.game.play.action.DefaultAction;
import org.mech.rougue.factory.Inject;

public class TurnFreezeAction extends DefaultAction {

	@Inject
	private State state;

	@Override
	protected void doInvoke() {
		state.setTurnFrozen(!state.isTurnFrozen());
	}

	@Override
	public String getActionType() {
		return "turn_freeze";
	}

}
