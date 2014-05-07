package org.mech.rougue.core.r.model.light;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.mech.rougue.core.game.model.map.Map;
import org.mech.rougue.core.r.event.RebuildLightEvent;
import org.mech.rougue.core.r.handler.game.light.LightMask;
import org.mech.terminator.geometry.GeometryUtils;
import org.mech.terminator.geometry.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractLightSource implements LightSource {

	public static final double DEFAULT_BORDER_INTENSITY = LightMask.DEFAULT_SHADOW_INTENSITY + 0.2;

	private final static Logger LOG = LoggerFactory.getLogger(AbstractLightSource.class);

	private final Set<LightPosition> lightPositions = new HashSet<LightPosition>();
	private Position position;

	@Override
	public void onLightRebuild(final RebuildLightEvent event) {
		rebuildLights(event.getContext().getData().getMap());
	}
	
	protected abstract void rebuildLights(Map map);
	
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
	public Collection<LightPosition> getLights() {
		return lightPositions;
	}

	protected void addLights(final Collection<Position> positions) {
		for (final Position p : positions) {
			final LightPosition lightPosition = new LightPosition(p);
			final double shadowIntensity = getShadowIntensity(GeometryUtils.distPyth(getPosition(), p));
			lightPosition.setShadowIntensity(shadowIntensity);
			lightPosition.setLightIntensity(Math.max(0, shadowIntensity - DEFAULT_BORDER_INTENSITY));
			lightPositions.add(lightPosition);
		}
	}

}
