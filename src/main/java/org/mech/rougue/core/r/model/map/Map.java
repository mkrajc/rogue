package org.mech.rougue.core.r.model.map;

import java.util.ArrayList;
import java.util.List;
import org.mech.rougue.core.game.model.map.MapStats;
import org.mech.rougue.core.game.model.map.tile.GroundTile;
import org.mech.rougue.core.game.model.map.tile.MapTile;
import org.mech.rougue.core.game.model.map.tile.NewMapTile;
import org.mech.rougue.core.game.model.map.tile.TileConfiguration;
import org.mech.rougue.core.game.model.map.tile.Tiles;
import org.mech.rougue.core.r.export.Exportable;
import org.mech.rougue.core.r.model.area.Area;
import org.mech.rougue.core.r.model.common.GObject;
import org.mech.rougue.utils.CollectionUtils;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.Position;

public class Map implements Exportable {
	private static final long serialVersionUID = 6589737997245185262L;

	private List<GObject> gameObjects;
	private String mapId;
	private NewMapTile[][] newArea;
	private Dimension mapSize;
	private MapStats stats;
	private Area area;

	public Map(final Dimension mapSize) {
		this(mapSize, Tiles.VOID);
	}

	public Map(final Dimension mapSize, final TileConfiguration defaultTileGround) {
		this.mapSize = mapSize;
		this.stats = new MapStats(this);
		this.newArea = new NewMapTile[mapSize.width][mapSize.height];

		final MapTile voidTile = new MapTile(Tiles.VOID);

		for (int i = 0; i < mapSize.width; i++) {
			for (int j = 0; j < mapSize.height; j++) {
				final NewMapTile newMapTile = new NewMapTile();
				newMapTile.setGround(new GroundTile(defaultTileGround));
				newArea[i][j] = newMapTile;
			}
		}

		gameObjects = new ArrayList<GObject>();
	}

	public NewMapTile get(final int x, final int y) {
		if (x < 0 || y < 0 || x >= mapSize.width || y >= mapSize.height) {
			return null;
		}
		return newArea[x][y];
	}

	public NewMapTile get(final Position p) {
		return get(p.x, p.y);
	}

	public void put(final NewMapTile tile, final int x, final int y) {
		newArea[x][y] = tile;
	}

	public void put(final NewMapTile tile, final Position p) {
		put(tile, p.x, p.y);
	}

	public Dimension getSize() {
		return mapSize;
	}

	public List<GObject> getGameObjects() {
		return gameObjects;
	}

	public void registerGameObject(final GObject gameObject) {
		this.gameObjects.add(gameObject);
	}

	public void registerGameObjects(final List<GObject> objects) {
		if (CollectionUtils.isNotEmpty(objects)) {
			for (final GObject object : objects) {
				registerGameObject(object);
			}
		}
	}

	public MapStats getStats() {
		return stats;
	}

	public void reset() {
		stats.reset();
		gameObjects.clear();
	}

	public boolean isPositionInMap(final Position position) {
		return !(position.x < 0 || position.x >= newArea.length || position.y < 0 || position.y >= newArea[0].length);
	}

	public void setStats(final MapStats mapStats) {
		this.stats = mapStats;
	}

	public void handleEnvironmentChange(final EnvironmentObject eObject) {
		final NewMapTile tile = get(eObject.getPosition());
		tile.setPassable(eObject.isPassable());
		tile.setObstacle(eObject.isLightObstacle());
	}

	@Override
	public String getObjectId() {
		return mapId;
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

}
