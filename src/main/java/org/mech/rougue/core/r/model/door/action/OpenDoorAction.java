package org.mech.rougue.core.r.model.door.action;

import org.mech.rougue.core.r.model.door.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpenDoorAction extends AbstractDoorAction {
	private final static Logger LOG = LoggerFactory.getLogger(OpenDoorAction.class);

	@Override
	public String getActionName() {
		return "open_door_action";
	}

	@Override
	public void doInvoke(Door door) {
		door.open();
		LOG.debug("door [" + door + "] opened");
	}

	@Override
	public boolean enabled(Door object) {
		return object.isClosed();
	}

}