package org.mech.rougue.core.r.event.player;

import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.r.event.Event;
import org.mech.rougue.core.r.event.player.PlayerChangeMapRequestEvent.Handler;
import org.mech.terminator.geometry.Position;

public class PlayerChangeMapRequestEvent extends Event<Handler> {
	public interface Handler extends Event.Handler {
		void onPlayerChangeMapRequestEvent(PlayerChangeMapRequestEvent event);
	}
	private final String fromMapId;
	private final String toMapId;
	private final Player player;
	private final Position destinationPosition;

	public PlayerChangeMapRequestEvent(final String fromMapId, final String toMapId, final Player player, final Position destinationPosition) {
		super();
		this.fromMapId = fromMapId;
		this.toMapId = toMapId;
		this.player = player;
		this.destinationPosition = destinationPosition;
	}

	@Override
	protected void onDispatch(final Handler h) {
		h.onPlayerChangeMapRequestEvent(this);
	}

	public String getFromMapId() {
		return fromMapId;
	}

	public String getToMapId() {
		return toMapId;
	}

	public Player getPlayer() {
		return player;
	}

	public Position getDestinationPosition() {
		return destinationPosition;
	}

}
