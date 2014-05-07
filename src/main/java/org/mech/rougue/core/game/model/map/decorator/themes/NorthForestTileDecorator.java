package org.mech.rougue.core.game.model.map.decorator.themes;

import java.util.Random;
import org.mech.rougue.core.game.model.map.decorator.tile.AbstractIdDecorator;
import org.mech.rougue.core.game.model.map.tile.Tiles;
import org.mech.terminator.geometry.Position;

public class NorthForestTileDecorator extends AbstractIdDecorator {

	@Override
	protected String getOrnament(String id, Position position) {
		if (id.equals(Tiles.ROOM_GROUND_ID)) {
			return "" + new Random(System.nanoTime()).nextInt(4);
		}
		return null;
	}
	
	@Override
	public String getId() {
		return "north.forest";
	}
}
