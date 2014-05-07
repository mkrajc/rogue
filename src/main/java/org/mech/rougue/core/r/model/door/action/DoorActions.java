package org.mech.rougue.core.r.model.door.action;

import java.util.List;
import org.mech.rougue.core.r.action.object.ObjectActionHelper;
import org.mech.rougue.core.r.model.door.Door;
import org.mech.rougue.factory.Inject;

public class DoorActions {

	@Inject
	private List<DoorAction> doorActions;

	public List<DoorAction> getDoorActions() {
		return doorActions;
	}

	public List<DoorAction> getActiveDoorActions(Door door) {
		return ObjectActionHelper.build(door, getDoorActions());
	}

}
