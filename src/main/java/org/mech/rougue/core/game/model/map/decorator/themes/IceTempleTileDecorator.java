package org.mech.rougue.core.game.model.map.decorator.themes;

import org.mech.rougue.core.game.model.map.decorator.tile.AbstractIdDecorator;
import org.mech.rougue.core.game.model.map.tile.Tiles;
import org.mech.terminator.geometry.Position;

public class IceTempleTileDecorator extends AbstractIdDecorator {

	@Override
	protected String getOrnament(String id, Position position) {
		//		if (tile.getId().getId().equals(Tiles.ROOM_GROUND_ID)) {
		//			if ((position.x + position.y) % 2 == 0) {
		//				return "0";
		//			} else {
		//				return "1";
		//			}
		//		}

		if (id.equals(Tiles.ROOM_GROUND_ID)) {
			int size = 6;
			int x = position.x % size;
			int y = position.y % size;
			int half = size / 2;
			if ((x >= half && y < half) || (x < half && y >= half)) {
				return "0";
			} else {
				return "1";
			}
		}
		return null;
	}

	@Override
	public String getId() {
		return "ice.temple";
	}
}
