package org.mech.rougue.core.game.model.map;

import java.util.ArrayList;
import java.util.List;
import org.mech.rougue.core.r.GObject;

public class MapUtils {
	public static <T> List<T> getObjectsOfType(Map map, Class<T> clazz) {
		List<T> objects = new ArrayList<T>();
		List<GObject> mapObjects = map.getGameObjects();

		if (mapObjects != null) {
			for (GObject mapObject : mapObjects) {
				if (clazz.isAssignableFrom(mapObject.getClass())) {
					objects.add((T) mapObject);
				}
			}
		}

		return objects;
	}

	public static <T> T getObjectOfType(Map map, Class<T> clazz) {
		List<T> objectsOfType = getObjectsOfType(map, clazz);
		return (objectsOfType == null || objectsOfType.size() != 1) ? null : objectsOfType.get(0);
	}
}
