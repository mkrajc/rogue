package org.mech.rougue.core.r.model.light;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.mech.rogue.game.model.map.Map;
import org.mech.rougue.core.game.GameData;
import org.mech.rougue.core.r.event.RebuildLightEvent;
import org.mech.rougue.core.r.handler.game.light.LightMask;

import org.mech.terminator.geometry.GeometryUtils;
import org.mech.terminator.geometry.Position;

public abstract class AbstractLightSource implements LightSource {

	public static final double DEFAULT_BORDER_INTENSITY = LightMask.DEFAULT_SHADOW_INTENSITY + 0.2;

	private final Set<Light> lightPositions = new HashSet<Light>();
	private Position position;

	@Override
	public void onLightRebuild(final RebuildLightEvent event) {
		rebuildLights(event.getContext().getData());
	}
	
	protected abstract void rebuildLights(GameData data);
	
	// TODO some generic value
	protected abstract double getShadowIntensity(float distanceFromCenter);

	@Override
	public void setPosition(final Position position) {
		this.position = position;
	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public Collection<Light> getLights() {
		return lightPositions;
	}

	protected void addLights(final Collection<Position> positions) {
		for (final Position p : positions) {
			final Light lightPosition = new Light(p);
			final double shadowIntensity = getShadowIntensity(GeometryUtils.distPyth(getPosition(), p));
			lightPosition.setShadowIntensity(shadowIntensity);
			lightPosition.setLightIntensity(Math.max(0, shadowIntensity - DEFAULT_BORDER_INTENSITY));
			lightPositions.add(lightPosition);
		}
	}

}
