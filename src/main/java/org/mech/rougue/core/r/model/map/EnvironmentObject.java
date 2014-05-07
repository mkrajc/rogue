package org.mech.rougue.core.r.model.map;

import org.mech.rougue.core.game.model.Positionable;
import org.mech.rougue.core.r.GObject;

public interface EnvironmentObject extends GObject, Positionable {
	
	boolean isPassable();
	boolean isLightObstacle();
}
