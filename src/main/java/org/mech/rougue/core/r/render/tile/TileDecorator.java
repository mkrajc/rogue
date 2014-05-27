package org.mech.rougue.core.r.render.tile;

import org.mech.terminator.geometry.Position;

public interface TileDecorator {
	
	public String decorate(String id, Position position);
}
