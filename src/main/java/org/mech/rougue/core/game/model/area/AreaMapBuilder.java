package org.mech.rougue.core.game.model.area;

import java.util.List;
import org.mech.rougue.core.game.model.map.Map;
import org.mech.rougue.core.game.model.map.tile.GroundTile;
import org.mech.rougue.core.game.model.map.tile.NewMapTile;
import org.mech.rougue.core.game.model.map.tile.TileConfiguration;
import org.mech.rougue.core.game.model.map.tile.Tiles;
import org.mech.rougue.core.game.model.room.Room;
import org.mech.rougue.core.r.model.door.Door;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.Line;
import org.mech.terminator.geometry.Position;
import org.mech.terminator.geometry.Rectangle;

public class AreaMapBuilder {

	public void createMap(Area area, Dimension dim) {
		final Map map = new Map(dim);

		for (Room room : area.rooms) {
			buildRoom(room, map);
		}

		wallize(map);
		
		for (Door door : area.doors) {
			NewMapTile newMapTile = map.get(door.getPosition());
			newMapTile.setWall(null);
			newMapTile.setPassable(door.isOpen());
			newMapTile.setObstacle(door.isClosed());
			
			map.registerGameObject(door);
		}

		map.registerGameObjects((List) area.getGates());


		area.map = map;

	}

	public void createMap(Area area) {

		int maxx = 0;
		int maxy = 0;

		for (Room room : area.rooms) {
			maxx = Math.max(room.getPosition().x + room.getSize().width, maxx);
			maxy = Math.max(room.getPosition().y + room.getSize().height, maxy);
		}

		createMap(area, Dimension.of(maxx, maxy));
	}

	private void wallize(Map map) {
		for (int i = 0; i < map.getSize().width; i++) {
			for (int j = 0; j < map.getSize().height; j++) {
				final Position at = Position.at(i, j);
				final NewMapTile mapTile = map.get(at);

//				boolean secretDoor = isSecretDoor(mapTile);

				if (isWall(mapTile)) {
//
					String suff = computeWallId(map, mapTile, at);
					mapTile.getWall().getRenderId().setSuffix(suff);
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

	private String computeWallId(Map map, NewMapTile mapTile, Position at) {
		Position n = at.addY(-1);
		Position s = at.addY(1);
		Position e = at.addX(1);
		Position w = at.addX(-1);

		boolean isNorthWall = isWall(map.get(n));
		boolean isSouthWall = isWall(map.get(s));
		boolean isWestWall = isWall(map.get(w));
		boolean isEastWall = isWall(map.get(e));

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

	private boolean isWall(NewMapTile mapTile) {
		return mapTile != null && mapTile.getWall() != null && Tiles.ROOM_WALL_ID.contains(mapTile.getWall().getId());
	}

	private void buildRoom(Room room, Map map) {
		final Dimension dim = room.getSize();
		final Position roomPosition = room.getPosition();

		Position end = roomPosition.add(dim.toPosition());

		for (int i = roomPosition.x; i < end.x; i++) {
			for (int j = roomPosition.y; j < end.y; j++) {
				Position at = Position.at(i, j);
				NewMapTile mapTile = new NewMapTile();
				mapTile.setGround(new GroundTile(Tiles.ROOM_GROUND));
				putTile(map, mapTile, at);
			}
		}

		final Rectangle roomRectangle = new Rectangle(roomPosition, dim);

		fillLine(map, roomRectangle.getLineUp(), Tiles.ROOM_WALL);
		fillLine(map, roomRectangle.getLineDown(), Tiles.ROOM_WALL);
		fillLine(map, roomRectangle.getLineLeft(), Tiles.ROOM_WALL);
		fillLine(map, roomRectangle.getLineRight(), Tiles.ROOM_WALL);

	}


	private void putTile(Map map, NewMapTile tile, Position position) {
		if (map.isPositionInMap(position)) {
			map.put(tile, position);
		}
	}

	private void fillLine(Map map, Line line, TileConfiguration tile) {
		List<Position> linePos = line.getPoints();
		for (Position position : linePos) {
			NewMapTile newMapTile = map.get(position);
			newMapTile.setWall(new GroundTile(tile));
			putTile(map, newMapTile, position);
		}
	}

	public void build(Area area) {
		createMap(area);
	}
}
