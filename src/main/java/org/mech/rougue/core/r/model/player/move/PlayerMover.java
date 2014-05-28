package org.mech.rougue.core.r.model.player.move;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.game.update.move.MapMover;
import org.mech.rougue.core.r.event.player.PlayerMoveEvent;
import org.mech.rougue.core.r.model.geom.Move;
import org.mech.rougue.core.r.model.map.Map;
import org.mech.terminator.geometry.Position;

public class PlayerMover extends MapMover {

	public void displacePlayer(final Player player, final Map map) {
		super.displace(player, player.getPosition(), map);
	}

	public void placePlayer(final GameContext context, final Player player, final Position position, final Map map, final boolean notify) {
		final Position old = player.getPosition();
		super.place(player, position, map);
		if (notify) {
			notifyPlayerMove(context, player, old, player.getPosition());
		}
	}

	public void movePlayer(final GameContext context, final Player player, final Position destination, final Map map) {
		final Position old = player.getPosition();
		final boolean moved = super.move(player, destination, map);

		if (moved) {
			notifyPlayerMove(context, player, old, destination);
		}
	}

	public void movePlayer(final GameContext context, final Player player, final Move move, final Map map) {
		movePlayer(context, player, shiftPosition(player.getPosition(), move), map);
	}

	private void notifyPlayerMove(final GameContext context, final Player player, final Position oldP, final Position newP) {
		new PlayerMoveEvent(player, oldP, newP).fire(context);

	}

}
