package org.mech.rougue.core.r.model.light;

import java.util.Collection;

import org.mech.rougue.core.game.model.light.LightType;
import org.mech.rougue.core.r.event.RebuildLightEvent;
import org.mech.rougue.core.r.model.common.GObject;
import org.mech.rougue.core.r.model.geom.Positionable;

public interface LightSource extends Positionable, GObject, RebuildLightEvent.Handler {

	LightType getLightType();

	Collection<Light> getLights();

}
