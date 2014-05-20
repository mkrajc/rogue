package org.mech.rougue.core.r.event.player;

import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.r.event.Event;
import org.mech.rougue.core.r.event.player.PlayerDiedEvent.Handler;

public class PlayerDiedEvent extends Event<Handler> {

	public interface Handler extends Event.Handler {
		void onPlayerDeath(final PlayerDiedEvent event);
	}
	private final Player player;

	public PlayerDiedEvent(final Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}

	@Override
	protected void onDispatch(final Handler h) {
		h.onPlayerDeath(this);
	}

}
