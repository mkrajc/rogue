package org.mech.rougue.core.r.model.map;

import java.util.ArrayList;
import java.util.List;

import org.mech.rogue.game.export.Exportable;
import org.mech.rogue.game.model.map.MapTile;
import org.mech.rogue.game.model.map.TileConfig;
import org.mech.rougue.core.game.model.map.MapStats;
import org.mech.rougue.core.game.model.map.tile.Tiles;
import org.mech.rougue.core.r.model.area.Area;
import org.mech.rougue.core.r.model.common.GObject;
import org.mech.rougue.utils.CollectionUtils;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.Position;

public class Map implements Exportable {
	private static final long serialVersionUID = 6589737997245185262L;

	private List<GObject> gameObjects;
	private String mapId;
	private MapTile[][] newArea;
	private Dimension mapSize;
	private MapStats stats;
	private Area area;

	public Map(final Dimension mapSize) {
		this(mapSize, Tiles.VOID);
	}

	public Map(final Dimension mapSize, final TileConfig defaultTileGround) {
		this.mapSize = mapSize;
		this.stats = new MapStats(this);
		this.newArea = new MapTile[mapSize.width][mapSize.height];

		for (int i = 0; i < mapSize.width; i++) {
			for (int j = 0; j < mapSize.height; j++) {
				//TODO renderId
				final MapTile newMapTile = new MapTile(defaultTileGround);
				newArea[i][j] = newMapTile;
			}
		}

		gameObjects = new ArrayList<GObject>();
	}

	public MapTile get(final int x, final int y) {
		if (x < 0 || y < 0 || x >= mapSize.width || y >= mapSize.height) {
			return null;
		}
		return newArea[x][y];
	}

	public MapTile get(final Position p) {
		return get(p.x, p.y);
	}

	public void put(final MapTile tile, final int x, final int y) {
		newArea[x][y] = tile;
	}

	public void put(final MapTile tile, final Position p) {
		put(tile, p.x, p.y);
	}

	public Dimension getSize() {
		return mapSize;
	}

	public void add(final GObject gameObject) {
		this.gameObjects.add(gameObject);
	}

	public void remove(final GObject gameObject) {
		this.gameObjects.remove(gameObject);
	}

	public void addAll(final List<GObject> objects) {
		if (CollectionUtils.isNotEmpty(objects)) {
			for (final GObject object : objects) {
				add(object);
			}
		}
	}

	public MapStats getStats() {
		return stats;
	}

	public boolean isPositionInMap(final Position position) {
		return !(position.x < 0 || position.x >= newArea.length || position.y < 0 || position.y >= newArea[0].length);
	}

	public void setStats(final MapStats mapStats) {
		this.stats = mapStats;
	}

	public void handleEnvironmentChange(final EnvironmentObject eObject) {
		final MapTile tile = get(eObject.getPosition());
		// TODO
		// tile.setPassable(eObject.isPassable());
		// tile.setObstacle(eObject.isLightObstacle());
	}

	public String getMapId() {
		return mapId;
	}

	public void setMapId(final String mapId) {
		this.mapId = mapId;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(final Area area) {
		this.area = area;
	}

	public List<GObject> getGameObjects() {
		return gameObjects;
	}

	@Override
	public String objectId() {
		return mapId;
	}
}
