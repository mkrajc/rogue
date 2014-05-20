package org.mech.rougue.core.r.action.object;

import org.mech.rougue.core.r.event.player.PlayerMoveEvent;

public class PlayerMoveOnItemInteraction extends PlayerMoveInteraction {

	public PlayerMoveOnItemInteraction(final InteractiveObject interactiveObject) {
		super(interactiveObject);
	}

	@Override
	protected boolean isInteractionAvailable(final PlayerMoveEvent e) {
		return e.getCurrentPosition().equals(getItemPosition());
	}

}
