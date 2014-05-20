package org.mech.rougue.core.r.event.player;

import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.r.event.Event;
import org.mech.rougue.core.r.event.player.PlayerMoveEvent.Handler;
import org.mech.rougue.core.r.model.geom.Positionable;
import org.mech.terminator.geometry.Position;

public class PlayerMoveEvent extends Event<Handler> {

	public interface Handler extends Event.Handler {
		void onPlayerMove(final PlayerMoveEvent event);
	}
	private final Player player;
	private final Position oldPosition;
	private final Position currentPosition;

	public PlayerMoveEvent(final Player player, final Position oldPosition, final Position currentPosition) {
		this.player = player;
		this.oldPosition = oldPosition;
		this.currentPosition = currentPosition;
	}

	public Player getPlayer() {
		return player;
	}

	public Position getOldPosition() {
		return oldPosition;
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}
	
	public boolean isPlayerStandingOn(final Positionable p){
		return p.getPosition().equals(currentPosition);
	}

	@Override
	protected void onDispatch(final Handler h) {
		h.onPlayerMove(this);
	}

}
