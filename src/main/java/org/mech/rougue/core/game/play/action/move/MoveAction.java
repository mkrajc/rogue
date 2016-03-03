package org.mech.rougue.core.game.play.action.move;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.game.play.action.ActionDispatcher;
import org.mech.rougue.core.game.play.action.DefaultAction;
import org.mech.rougue.core.r.model.geom.Move;
import org.mech.rougue.core.r.model.player.move.PlayerMover;
import org.mech.rougue.factory.Inject;

public abstract class MoveAction extends DefaultAction {

	private PlayerMover mover = new PlayerMover();

	@Override
	protected void doInvoke(GameContext ctx) {
		final org.mech.rogue.game.model.map.Map map = ctx.getData().getMap();
		final Player player = ctx.getData().getPlayer();

		mover.movePlayer(ctx, player, getMove(), map);

	}

	protected abstract Move getMove();

}
