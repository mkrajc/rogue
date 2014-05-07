package org.mech.rougue.core.game.play.action.move;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.game.play.action.ActionDispatcher;
import org.mech.rougue.core.game.play.action.DefaultAction;
import org.mech.rougue.core.game.update.move.MapMover;
import org.mech.rougue.core.r.event.PlayerMoveEvent;
import org.mech.rougue.core.r.model.geom.Move;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.geometry.Position;

public abstract class MoveAction extends DefaultAction {

	@Inject
	private MapMover mapMover;

	@Inject
	private GameContext context;

	@Inject
	private ActionDispatcher actionDispatcher;

	@Override
	protected void doInvoke() {
		final org.mech.rougue.core.game.model.map.Map map = context.getData().getMap();
		final Player player = context.getData().getPlayer();
		final Position oldPosition = player.getPosition();
		final boolean moved = mapMover.move(player, getMove(), map);

		if (moved) {
			notifyPlayerMove(player, oldPosition, player.getPosition());
		}

	}

	private void notifyPlayerMove(final Player player, final Position oldP, final Position newP) {
		new PlayerMoveEvent(player, oldP, newP).fire(context);

	}

	protected abstract Move getMove();

}
