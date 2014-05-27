package org.mech.rougue.core.game.model.area;

import java.util.List;
import org.mech.rougue.core.game.model.map.tile.GroundTile;
import org.mech.rougue.core.game.model.map.tile.NewMapTile;
import org.mech.rougue.core.game.model.map.tile.TileConfiguration;
import org.mech.rougue.core.game.model.map.tile.Tiles;
import org.mech.rougue.core.game.model.room.Room;
import org.mech.rougue.core.r.model.combat.dmg.PhysicalDamage;
import org.mech.rougue.core.r.model.door.Door;
import org.mech.rougue.core.r.model.inv.Item;
import org.mech.rougue.core.r.model.inv.ItemMapObject;
import org.mech.rougue.core.r.model.inv.item.Clothes;
import org.mech.rougue.core.r.model.inv.item.Jewel;
import org.mech.rougue.core.r.model.inv.item.weapon.OneHandedWeapon;
import org.mech.rougue.core.r.model.inv.item.weapon.TwoHandedWeapon;
import org.mech.rougue.core.r.model.inv.item.weapon.WeaponType;
import org.mech.rougue.core.r.model.map.Map;
import org.mech.rougue.core.r.model.trap.DamageTrap;
import org.mech.rougue.core.r.model.trap.Trap;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.Line;
import org.mech.terminator.geometry.Position;
import org.mech.terminator.geometry.Rectangle;

public class AreaMapBuilder {

	public void createMap(final Area area, final Dimension dim) {
		final Map map = new Map(dim);

		for (final Room room : area.rooms) {
			buildRoom(room, map);
		}

		wallize(map);

		for (final Door door : area.doors) {
			final NewMapTile newMapTile = map.get(door.getPosition());
			newMapTile.setWall(null);
			newMapTile.setPassable(door.isOpen());
			newMapTile.setObstacle(door.isClosed());

			map.registerGameObject(door);
		}

		map.registerGameObjects((List) area.getGates());

		final Trap t = new DamageTrap(new PhysicalDamage(30));
		t.setPosition(Position.at(7, 5));
		map.registerGameObject(t);

		//		final Monster m = new Monster();
		final Jewel test = new Jewel();
		test.setName("Nahrdelnik");
		final ItemMapObject m = new ItemMapObject(test);
		m.setPosition(Position.at(7, 1));
		map.registerGameObject(m);

		final Item clothes = new Clothes();
		final ItemMapObject w = new ItemMapObject(clothes);
		w.setPosition(Position.at(6, 2));
		map.registerGameObject(w);

		final OneHandedWeapon dagger = new OneHandedWeapon();
		dagger.setName("dagger");
		dagger.setWeight(0.3F);
		dagger.setWeaponType(WeaponType.DAGGER);

		final ItemMapObject d = new ItemMapObject(dagger);
		d.setPosition(Position.at(3, 3));
		map.registerGameObject(d);

		final OneHandedWeapon dagger2 = new OneHandedWeapon();
		dagger2.setName("dagger2");
		dagger2.setWeight(0.4F);
		dagger2.setWeaponType(WeaponType.DAGGER);

		final ItemMapObject d2 = new ItemMapObject(dagger2);
		d2.setPosition(Position.at(4, 3));
		map.registerGameObject(d2);

		final TwoHandedWeapon axe = new TwoHandedWeapon();
		axe.setName("axe");
		axe.setWeight(2.3F);
		axe.setWeaponType(WeaponType.SWORD);

		final ItemMapObject spearObj = new ItemMapObject(axe);
		spearObj.setPosition(Position.at(2, 3));
		map.registerGameObject(spearObj);

		area.map = map;

	}

	public void createMap(final Area area) {

		int maxx = 0;
		int maxy = 0;

		for (final Room room : area.rooms) {
			maxx = Math.max(room.getPosition().x + room.getSize().width, maxx);
			maxy = Math.max(room.getPosition().y + room.getSize().height, maxy);
		}

		createMap(area, Dimension.of(maxx, maxy));
	}

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

	private void buildRoom(final Room room, final Map map) {
		final Dimension dim = room.getSize();
		final Position roomPosition = room.getPosition();

		final Position end = roomPosition.add(dim.toPosition());

		for (int i = roomPosition.x; i < end.x; i++) {
			for (int j = roomPosition.y; j < end.y; j++) {
				final Position at = Position.at(i, j);
				final NewMapTile mapTile = new NewMapTile();
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

	public void build(final Area area) {
		createMap(area);
	}
}
