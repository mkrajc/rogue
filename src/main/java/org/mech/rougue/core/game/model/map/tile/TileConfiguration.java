package org.mech.rougue.core.game.model.map.tile;

public class TileConfiguration {
	private String id;

	private boolean passable;
	private boolean obstacle = false;

	public TileConfiguration(String id, boolean isPassable) {
		this.id = id;
		this.passable = isPassable;
	}

	public TileConfiguration(String id, boolean isPassable, boolean obstacle) {
		this(id, isPassable);
		this.obstacle = obstacle;
	}

	public boolean isPassable() {
		return passable;
	}

	public String getId() {
		return id;
	}
	
	public boolean is(String id){
		return this.id.equals(id);
	}

	public boolean isObstacle() {
		return obstacle;
	}

	public void setObstacle(boolean obstacle) {
		this.obstacle = obstacle;
	}

	public void setPassable(boolean passable) {
		this.passable = passable;
	}

	@Override
	public String toString() {
		return id + ", pass=" + passable + ", obs= " + obstacle;
	}

}
