package org.mech.rougue.core.game.model.map.tile;

import java.io.Serializable;

import org.mech.rogue.game.model.map.TileConfig;
import org.mech.rougue.core.r.render.RenderId;

public class GroundTile implements Serializable{
	String id;
	RenderId renderId;
	
	boolean passable;
	boolean obstacle;
	
	public GroundTile(final TileConfig configuration) {
		this.id = configuration.id();
		this.renderId = new RenderId(id);
		this.passable = configuration.isPassable();
		this.obstacle = configuration.isObstacle();
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public RenderId getRenderId() {
		return renderId;
	}

	public void setRenderId(final RenderId renderId) {
		this.renderId = renderId;
	}

	public boolean isPassable() {
		return passable;
	}

	public void setPassable(final boolean passable) {
		this.passable = passable;
	}

	public boolean isObstacle() {
		return obstacle;
	}

	public void setObstacle(final boolean obstacle) {
		this.obstacle = obstacle;
	}
}
