package org.mech.rougue.core.game.model.map.render;

import org.mech.rougue.core.r.model.common.GObject;
import org.mech.rougue.core.r.model.geom.Positionable;

public interface MapObject extends Positionable, GObject {

	int getRenderOptions();
	
	RenderId getRenderId();

	String getType();

}
