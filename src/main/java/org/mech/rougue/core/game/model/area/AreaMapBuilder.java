package org.mech.rougue.core.game.model.area;

import java.util.List;
import org.mech.rougue.core.game.model.map.tile.GroundTile;
import org.mech.rougue.core.game.model.map.tile.NewMapTile;
import org.mech.rougue.core.game.model.map.tile.TileConfiguration;
import org.mech.rougue.core.game.model.map.tile.Tiles;
import org.mech.rougue.core.r.model.map.Map;
import org.mech.terminator.geometry.Line;
import org.mech.terminator.geometry.Position;

public class AreaMapBuilder {


	private void wallize(final Map map) {
		for (int i = 0; i < map.getSize().width; i++) {
			for (int j = 0; j < map.getSize().height; j++) {
				final Position at = Position.at(i, j);
				final NewMapTile mapTile = map.get(at);

				//				boolean secretDoor = isSecretDoor(mapTile);

				if (isWall(mapTile)) {
					//
					final String suff = computeWallId(map, mapTile, at);
					mapTile.getWall().getRenderId().append(suff);
					//
					//					if (secretDoor) {
					//						mapTile.getId().setId(Tiles.ROOM_WALL_ID);
					//					}
				}
			}
		}
	}

	//	private boolean isSecretDoor(NewMapTile mapTile) {
	//		return mapTile != null && Tiles.SECRET_ID.equals(mapTile.getId().getOrnament());
	//	}

	private String computeWallId(final Map map, final NewMapTile mapTile, final Position at) {
		final Position n = at.addY(-1);
		final Position s = at.addY(1);
		final Position e = at.addX(1);
		final Position w = at.addX(-1);

		final boolean isNorthWall = isWall(map.get(n));
		final boolean isSouthWall = isWall(map.get(s));
		final boolean isWestWall = isWall(map.get(w));
		final boolean isEastWall = isWall(map.get(e));

		String wallIdOrnament = "";

		if (isNorthWall) {
			wallIdOrnament += "n";
		}

		if (isSouthWall) {
			wallIdOrnament += "s";
		}

		if (isWestWall) {
			wallIdOrnament += "w";
		}

		if (isEastWall) {
			wallIdOrnament += "e";
		}

		return wallIdOrnament;
	}

	private boolean isWall(final NewMapTile mapTile) {
		return mapTile != null && mapTile.getWall() != null && Tiles.ROOM_WALL_ID.contains(mapTile.getWall().getId());
	}


	private void putTile(final Map map, final NewMapTile tile, final Position position) {
		if (map.isPositionInMap(position)) {
			map.put(tile, position);
		}
	}

	private void fillLine(final Map map, final Line line, final TileConfiguration tile) {
		final List<Position> linePos = line.getPoints();
		for (final Position position : linePos) {
			final NewMapTile newMapTile = map.get(position);
			newMapTile.setWall(new GroundTile(tile));
			putTile(map, newMapTile, position);
		}
	}

}
