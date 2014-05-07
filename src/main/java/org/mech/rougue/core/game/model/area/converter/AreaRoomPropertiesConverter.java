package org.mech.rougue.core.game.model.area.converter;

import static org.mech.rougue.utils.PropertyUtils.*;
import java.util.Properties;
import org.mech.rougue.core.game.model.area.Area;
import org.mech.rougue.core.game.model.room.Room;

public class AreaRoomPropertiesConverter extends AreaItemPropertiesConverter {
	private static final String ROOM = "room";

	@Override
	protected String getItemKey() {
		return ROOM;
	}

	@Override
	protected void doConvert(Properties roomProperties, Area area, String id) {
		Room room = new Room();

		room.setPosition(loadOrThrowPosition(roomProperties, POSITION));
		room.setSize(loadOrThrowDimension(roomProperties, DIMENSION));
		room.setName(loadString(roomProperties, NAME));

		area.getRooms().add(room);

	}

}
