package org.mech.rougue.core.r.action.object;

import org.mech.rougue.core.r.event.PlayerMoveEvent;
import org.mech.terminator.geometry.GeometryUtils;

public class PlayerMoveInFrontOfItemInteraction extends PlayerMoveInteraction {

	public PlayerMoveInFrontOfItemInteraction(final InteractiveObject interactiveObject) {
		super(interactiveObject);
	}

	@Override
	protected boolean isInteractionAvailable(final PlayerMoveEvent e) {
		return GeometryUtils.distPyth(e.getCurrentPosition(), getItemPosition()) == 1.0;
	}

}
