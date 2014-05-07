package org.mech.rougue.core.game.play.action.move;

import org.mech.rougue.core.game.model.Move;

public class MoveNorthAction extends MoveAction {

	@Override
	public String getActionType() {
		return "move_north";
	}

	@Override
	protected Move getMove() {
		return Move.N;
	}

}
