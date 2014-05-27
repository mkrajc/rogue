package org.mech.rougue.core.game.model.map.decorator.themes;

import java.util.Random;
import org.mech.rougue.core.game.model.map.tile.Tiles;
import org.mech.rougue.core.r.render.tile.TileDecorator;
import org.mech.terminator.geometry.Position;

public class NorthForestTileDecorator implements TileDecorator {

	@Override
	public String decorate(final String id, final Position position) {
		if (id.equals(Tiles.ROOM_GROUND_ID)) {
			return "" + new Random(System.nanoTime()).nextInt(4);
		}
		return null;
	}

	public String getId() {
		return "north.forest";
	}
}
