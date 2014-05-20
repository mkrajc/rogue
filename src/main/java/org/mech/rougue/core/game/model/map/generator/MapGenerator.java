package org.mech.rougue.core.game.model.map.generator;

import org.mech.rougue.core.r.model.map.Map;
import org.mech.terminator.geometry.Dimension;

public class MapGenerator {

	public static Map generateRoom(Dimension dim) {
		int height = dim.height;
		int width = dim.width;

		final Map map = new Map(dim);
		//
		//	for (int i = 0; i < width; i++) {
		//	    for (int j = 0; j < height; j++) {
		//		if (i == 0 || j == 0 || i == width - 1 || j == height - 1) {
		//		    map.put(Tiles.ROOM_WALL, i, j);
		//		} else {
		//		    map.put(Tiles.ROOM_GROUND, i, j);
		//		}
		//
		//	    }
		//	}

		return map;
	}

}
