package org.mech.rougue.core.game.play.action.turn;

import org.mech.rougue.core.game.play.action.DefaultAction;
import org.mech.rougue.core.game.state.GameState;
import org.mech.rougue.factory.Inject;

public class TurnFreezeAction extends DefaultAction {

	@Inject
	private GameState state;

	@Override
	protected void doInvoke() {
		state.setTurnFreezed(!state.isTurnFreezed());
	}

	@Override
	public String getActionType() {
		return "turn_freeze";
	}

}
