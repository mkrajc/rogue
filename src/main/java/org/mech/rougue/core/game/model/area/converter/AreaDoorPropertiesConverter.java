package org.mech.rougue.core.game.model.area.converter;

import static org.mech.rougue.utils.PropertyUtils.*;
import java.util.Properties;
import org.mech.rougue.core.game.model.area.Area;
import org.mech.rougue.core.game.model.door.LockableDoor;
import org.mech.rougue.core.r.model.door.Door;

public class AreaDoorPropertiesConverter extends AreaItemPropertiesConverter {
	private static final String DOOR = "door";

	private static final String OPENED = "opened";
	private static final String SECRET = "secret";

	private static final String LOCKABLE = "lockable";
	private static final String LOCKABLE_KEY = LOCKABLE + ".keyId";
	private static final String LOCKABLE_LOCKED = LOCKABLE + ".locked";

	@Override
	protected String getItemKey() {
		return DOOR;
	}

	@Override
	protected void doConvert(Properties doorProperties, Area area, String id) {
		boolean lockable = loadBoolean(doorProperties, LOCKABLE, false);

		Door door = lockable ? handleLockableDoor(doorProperties) : new Door();

		door.setOpen(loadBoolean(doorProperties, OPENED, true));
//		door.setSecret(loadBoolean(doorProperties, SECRET, false));
		door.setPosition(loadOrThrowPosition(doorProperties, POSITION));

		area.getDoors().add(door);

	}

	private LockableDoor handleLockableDoor(Properties doorProps) {
		LockableDoor door = new LockableDoor();
		door.setKeyId(loadOrThrowString(doorProps, LOCKABLE_KEY));
		door.setLocked(loadBoolean(doorProps, LOCKABLE_LOCKED, false));
		return door;
	}

}
