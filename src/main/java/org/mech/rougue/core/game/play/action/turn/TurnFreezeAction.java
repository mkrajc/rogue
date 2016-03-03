package org.mech.rougue.core.game.play.action.turn;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.play.action.DefaultAction;

public class TurnFreezeAction extends DefaultAction {

	@Override
	protected void doInvoke(GameContext context) {
		context.getState().setTurnFrozen(!context.getState().isTurnFrozen());
	}

	@Override
	public String getActionType() {
		return "turn_freeze";
	}

}
