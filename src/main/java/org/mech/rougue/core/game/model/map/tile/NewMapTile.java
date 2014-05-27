package org.mech.rougue.core.game.model.map.tile;

import java.util.ArrayList;
import java.util.List;
import org.mech.rougue.core.r.render.RenderId;

public class NewMapTile {
	private GroundTile ground;
	private GroundTile wall;
	private boolean occupied = false;
	private boolean passable = true;
	private boolean obstacle = false;

	public GroundTile getGround() {
		return ground;
	}
	public void setGround(GroundTile ground) {
		this.ground = ground;
	}
	public GroundTile getWall() {
		return wall;
	}
	public void setWall(GroundTile wall) {
		this.wall = wall;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	public boolean isFreeForMove() {
		return !isOccupied() && isPassable();
	}
	public List<RenderId> getRenderIds() {
		List<RenderId> ids = new ArrayList<RenderId>();
		ids.add(ground.renderId);

		if (wall != null) {
			ids.add(wall.renderId);
		}
		return ids;

	}
	public boolean isPassable() {
		return passable && ground.isPassable() && (wall == null || wall.isPassable());
	}
	public void setPassable(boolean passable) {
		this.passable = passable;
	}
	public boolean isObstacle() {
		return obstacle || ground.isObstacle() || (wall != null && wall.isObstacle());
	}
	public void setObstacle(boolean obstacle) {
		this.obstacle = obstacle;
	}

}
