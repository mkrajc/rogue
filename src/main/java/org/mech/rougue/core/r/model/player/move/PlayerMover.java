package org.mech.rougue.core.r.model.player.move;

import org.mech.rogue.game.action.map.MapMovement;
import org.mech.rogue.game.action.map.NormalMapMovement;
import org.mech.rogue.game.model.map.Map;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.r.event.player.PlayerMoveEvent;
import org.mech.rougue.core.r.model.geom.Move;
import org.mech.terminator.geometry.Position;

import scala.Option;

public class PlayerMover {

	private MapMovement mapMovement = new NormalMapMovement();

	public void displacePlayer(final Player player, final Map map) {
		player.setPosition(null);
	}

	public void placePlayer(final GameContext context, final Player player, final Position position, final Map map, final boolean notify) {
		final Position old = player.getPosition();
		Option<Position> newPos = mapMovement.place(position, map);
		player.setPosition(newPos.get());
		if (notify) {
			notifyPlayerMove(context, player, old, player.getPosition());
		}
	}

	public void movePlayer(final GameContext context, final Player player, final Position destination, final Map map) {
		final Position old = player.getPosition();
		final Option<Position> newPos = mapMovement.move(old, destination, map);
		boolean moved = (newPos.isDefined() && !newPos.get().equals(old));
		if (moved) {
			player.setPosition(newPos.get());
			notifyPlayerMove(context, player, old, destination);
		}
	}

	public void movePlayer(final GameContext context, final Player player, final Move move, final Map map) {
		movePlayer(context, player, move.shift(player.getPosition()), map);
	}

	private void notifyPlayerMove(final GameContext context, final Player player, final Position oldP, final Position newP) {
		new PlayerMoveEvent(player, oldP, newP).fire(context);

	}

}
