package org.mech.rougue.core.game.play.action.move;

import org.mech.rougue.core.game.model.Move;

public class MoveEastAction extends MoveAction {

	@Override
	public String getActionType() {
		return "move_east";
	}

	@Override
	protected Move getMove() {
		return Move.E;
	}

}
