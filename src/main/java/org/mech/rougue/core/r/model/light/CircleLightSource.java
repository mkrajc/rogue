package org.mech.rougue.core.r.model.light;

import java.util.Collection;
import org.mech.rougue.core.game.model.light.LightType;
import org.mech.rougue.core.game.model.map.Map;
import org.mech.rougue.core.game.model.player.FOV;
import org.mech.rougue.core.r.event.EventBus;
import org.mech.rougue.core.r.event.RebuildLightEvent;
import org.mech.rougue.core.r.object.GId;
import org.mech.rougue.core.r.object.GIdFactory;
import org.mech.terminator.geometry.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CircleLightSource extends AbstractLightSource {

	private final static Logger LOG = LoggerFactory.getLogger(CircleLightSource.class);

	private final GId id;

	private int radius = 7;

	public CircleLightSource(final int radius) {
		this.radius = radius;
		this.id = GIdFactory.next();
	}

	@Override
	public GId id() {
		return id;
	}

	@Override
	public LightType getLightType() {
		return LightType.CIRCLE;
	}

	public int getRadius() {
		return radius;
	}
	
	@Override
	protected double getShadowIntensity(final float distanceFromCenter) {
		// from 1 to radius
		// 0 = 1
		// radius = DEFAULT_BORDER_INTENSITY

		// y = ax + b
		// a = (D-1)/r
		// b = 1
		// f(i) = ai + b

		final double a = (DEFAULT_BORDER_INTENSITY - 1) / (getRadius() - 1);
		final double b = 1;

		return a * distanceFromCenter + b;
	}

	@Override
	protected void rebuildLights(final Map gameMap) {
		LOG.debug("Recounting light on light source [" + this + "]");
		getLights().clear();

		final Collection<Position> positions = FOV.doFov(gameMap, getPosition(), getRadius());
		addLights(positions);
	}

	@Override
	public void registerHandlers(final EventBus bus) {
		bus.addHandler(RebuildLightEvent.class, this);
	}

	

	

}
