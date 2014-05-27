package org.mech.rougue.core.game.model.map.decorator.themes;

import org.mech.rougue.core.game.model.map.tile.Tiles;
import org.mech.rougue.core.r.render.tile.TileDecorator;
import org.mech.terminator.geometry.Position;

public class IceTempleTileDecorator implements TileDecorator {

	@Override
	public String decorate(final String id, final Position position) {

		if (id.equals(Tiles.ROOM_GROUND_ID)) {
			final int size = 6;
			final int x = position.x % size;
			final int y = position.y % size;
			final int half = size / 2;
			if ((x >= half && y < half) || (x < half && y >= half)) {
				return "0";
			} else {
				return "1";
			}
		}
		return null;
	}

	public String getId() {
		return "ice.temple";
	}

}
