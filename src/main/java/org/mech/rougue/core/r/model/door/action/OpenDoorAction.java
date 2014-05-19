package org.mech.rougue.core.r.model.door.action;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.model.door.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpenDoorAction extends AbstractDoorAction {
	private final static Logger LOG = LoggerFactory.getLogger(OpenDoorAction.class);
	public OpenDoorAction(final GameContext ctx, final Door door) {
		super(ctx, door);
	}

	@Override
	public String getActionName() {
		return "i_open_door";
	}

	@Override
	public void doInvoke(final Door door) {
		door.open();
		LOG.debug("door [" + door + "] opened");
	}

	@Override
	public boolean enabled() {
		return door.isClosed();
	}

}