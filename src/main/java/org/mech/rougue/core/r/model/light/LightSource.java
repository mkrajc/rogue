package org.mech.rougue.core.r.model.light;

import java.util.Collection;
import org.mech.rougue.core.game.model.Positionable;
import org.mech.rougue.core.game.model.light.LightType;
import org.mech.rougue.core.r.GObject;
import org.mech.rougue.core.r.event.RebuildLightEvent;

public interface LightSource extends Positionable, GObject, RebuildLightEvent.Handler {

	LightType getLightType();

	Collection<LightPosition> getLights();

}
