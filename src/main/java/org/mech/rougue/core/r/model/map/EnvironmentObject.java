package org.mech.rougue.core.r.model.map;

import org.mech.rougue.core.r.model.common.GObject;
import org.mech.rougue.core.r.model.geom.Positionable;

public interface EnvironmentObject extends GObject, Positionable {
	
	boolean isPassable();
	boolean isLightObstacle();
}
