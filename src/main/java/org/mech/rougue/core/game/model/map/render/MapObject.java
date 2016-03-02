package org.mech.rougue.core.game.model.map.render;

import org.mech.rogue.game.render.map.RenderOption;
import org.mech.rougue.core.r.model.common.GObject;
import org.mech.rougue.core.r.model.geom.Positionable;
import org.mech.rougue.core.r.render.RenderId;

public interface MapObject extends Positionable, GObject {

	RenderOption getRenderOptions();
	
	RenderId getRenderId();

	String getType();

}
