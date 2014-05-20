package org.mech.rougue.core.r.model.map;

import java.util.ArrayList;
import java.util.List;
import org.mech.rougue.core.game.model.map.MapStats;
import org.mech.rougue.core.game.model.map.tile.GroundTile;
import org.mech.rougue.core.game.model.map.tile.MapTile;
import org.mech.rougue.core.game.model.map.tile.NewMapTile;
import org.mech.rougue.core.game.model.map.tile.Tiles;
import org.mech.rougue.core.r.model.common.GObject;
import org.mech.rougue.utils.CollectionUtils;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.Position;

public class Map {
	
	private List<GObject> gameObjects = new ArrayList<GObject>();

	private NewMapTile[][] newArea;
	private Dimension mapSize;
	private MapStats stats;

	public Map(Dimension mapSize) {
		this.mapSize = mapSize;
		this.stats = new MapStats(this);
		this.newArea = new NewMapTile[mapSize.width][mapSize.height];

		final MapTile voidTile = new MapTile(Tiles.VOID);

		for (int i = 0; i < mapSize.width; i++) {
			for (int j = 0; j < mapSize.height; j++) {
				final NewMapTile newMapTile = new NewMapTile();
				newMapTile.setGround(new GroundTile(Tiles.VOID));
				newArea[i][j] = newMapTile;
			}
		}
	}

	public NewMapTile get(int x, int y) {
		if (x < 0 || y < 0 || x >= mapSize.width || y >= mapSize.height) {
			return null;
		}
		return newArea[x][y];
	}

	public NewMapTile get(Position p) {
		return get(p.x, p.y);
	}

	public void put(NewMapTile tile, int x, int y) {
		newArea[x][y] = tile;
	}

	public void put(NewMapTile tile, Position p) {
		put(tile, p.x, p.y);
	}

	public Dimension getSize() {
		return mapSize;
	}

	public List<GObject> getGameObjects() {
		return gameObjects;
	}

	public void registerGameObject(GObject gameObject) {
		this.gameObjects.add(gameObject);
	}
	
	public void registerGameObjects(List<GObject> objects) {
		if (CollectionUtils.isNotEmpty(objects)) {
			for (GObject object : objects) {
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

	public boolean isPositionInMap(Position position) {
		return !(position.x < 0 || position.x >= newArea.length || position.y < 0 || position.y >= newArea[0].length);
	}

	public void setStats(MapStats mapStats) {
		this.stats = mapStats;
	}
	
	public void handleEnvironmentChange(EnvironmentObject eObject){
		final NewMapTile tile = get(eObject.getPosition());
		tile.setPassable(eObject.isPassable());
		tile.setObstacle(eObject.isLightObstacle());
	}

	

}
