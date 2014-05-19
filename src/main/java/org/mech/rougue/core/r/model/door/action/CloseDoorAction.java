package org.mech.rougue.core.r.model.door.action;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.model.door.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloseDoorAction extends AbstractDoorAction {
	private final static Logger LOG = LoggerFactory.getLogger(CloseDoorAction.class);
	
	public CloseDoorAction(final GameContext ctx, final Door door) {
		super(ctx, door);
	}
	
	@Override
	public String getActionName() {
		return "close_door_action";
	}

	@Override
	public void doInvoke(final Door door) {
		door.close();
		LOG.debug("door [" + door + "] closed");
	}

	@Override
	public boolean enabled() {
		return door.isOpen();
	}

}
