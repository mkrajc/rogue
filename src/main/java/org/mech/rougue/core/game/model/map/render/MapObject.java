package org.mech.rougue.core.game.model.map.render;

import org.mech.rougue.core.game.model.Positionable;
import org.mech.rougue.core.r.GObject;

public interface MapObject extends Positionable, GObject {

	int getRenderOptions();
	
	RenderId getRenderId();

	String getType();

}
