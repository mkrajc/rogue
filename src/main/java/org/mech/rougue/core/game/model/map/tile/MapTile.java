package org.mech.rougue.core.game.model.map.tile;

public class MapTile {
	private TileConfiguration config;
	private TileID id;
	private boolean covered = true;
	private boolean occupied = false;

	public MapTile(TileConfiguration t) {
		this.config = t;
		this.id = new TileID(t);
	}

	public boolean isCovered() {
		return covered;
	}

	public void setCovered(boolean covered) {
		this.covered = covered;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public boolean isFreeForMove() {
		return !isOccupied() && getTileConfig().isPassable();
	}

	public boolean isPassable() {
		return getTileConfig().isPassable();
	}

	public boolean isObstacle() {
		return getTileConfig().isObstacle();
	}

	public TileConfiguration getTileConfig() {
		return config;
	}

	public TileID getId() {
		return id;
	}

	public void setTileConfig(TileConfiguration tileConfiguration) {
		this.config = tileConfiguration;
		this.id = new TileID(tileConfiguration);
	}

}
