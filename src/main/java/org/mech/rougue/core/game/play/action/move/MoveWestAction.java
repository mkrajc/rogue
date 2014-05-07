package org.mech.rougue.core.game.play.action.move;

import org.mech.rougue.core.r.model.geom.Move;

public class MoveWestAction extends MoveAction {

	@Override
	public String getActionType() {
		return "move_west";
	}

	@Override
	protected Move getMove() {
		return Move.W;
	}

}
