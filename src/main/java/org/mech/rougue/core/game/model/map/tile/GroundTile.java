package org.mech.rougue.core.game.model.map.tile;

import org.mech.rougue.core.game.model.map.render.RenderId;

public class GroundTile {
	String id;
	RenderId renderId;
	
	boolean passable;
	boolean obstacle;
	
	public GroundTile(TileConfiguration configuration) {
		this.id = configuration.getId();
		this.renderId = new RenderId();
		this.renderId.setId(id);
		
		this.passable = configuration.isPassable();
		this.obstacle = configuration.isObstacle();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public RenderId getRenderId() {
		return renderId;
	}

	public void setRenderId(RenderId renderId) {
		this.renderId = renderId;
	}

	public boolean isPassable() {
		return passable;
	}

	public void setPassable(boolean passable) {
		this.passable = passable;
	}

	public boolean isObstacle() {
		return obstacle;
	}

	public void setObstacle(boolean obstacle) {
		this.obstacle = obstacle;
	}
}
