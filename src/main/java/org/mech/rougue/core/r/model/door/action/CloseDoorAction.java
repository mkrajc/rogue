package org.mech.rougue.core.r.model.door.action;

import org.mech.rougue.core.r.model.door.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloseDoorAction extends AbstractDoorAction{
	private final static Logger LOG = LoggerFactory.getLogger(CloseDoorAction.class);
	
	@Override
	public String getActionName() {
		return "close_door_action";
	}

	@Override
	public void doInvoke(Door door) {
		door.close();
		LOG.debug("door [" + door + "] closed");
	}

	@Override
	public boolean enabled(Door object) {
		return object.isOpen();
	}

}
