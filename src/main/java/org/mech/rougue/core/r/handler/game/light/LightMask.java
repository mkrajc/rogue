package org.mech.rougue.core.r.handler.game.light;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.handler.game.UpdateAwareGObject;
import org.mech.rougue.core.r.model.light.LightPosition;
import org.mech.rougue.core.r.model.light.LightSource;
import org.mech.rougue.core.r.object.GId;
import org.mech.rougue.core.r.object.GIdFactory;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.geometry.Position;

public class LightMask implements UpdateAwareGObject {

	public static final double DEFAULT_SHADOW_INTENSITY = 0.5;
	public static final double DEFAULT_LIGHT_INTENSITY = 0.0;

	private List<LightPosition> lightMask = new ArrayList<LightPosition>();
	private GId gId;

	@Inject
	private GameContext gContext;

	public LightMask() {
		this.gId = GIdFactory.next();
	}

	@PostConstruct
	public void setup() {
		gContext.add(this);
	}

	public boolean isLighten(Position position) {
		return getLight(position) != null;
	}

	public void reset() {
		lightMask.clear();
	}

	public void light(LightPosition lightPosition) {
		LightPosition common = getLight(lightPosition);
		if (common == null) {
			lightMask.add(LightPosition.clone(lightPosition));
		} else {
			double newLightIntensity = mergeLight(lightPosition.getLightIntensity(), common.getLightIntensity());
			double newShadowIntensity = mergeShadow(lightPosition.getShadowIntensity(), common.getShadowIntensity());
			common.setShadowIntensity(newShadowIntensity);
			common.setLightIntensity(newLightIntensity);
		}
	}

	public double getShadowIntensity(Position p) {
		LightPosition light = getLight(p);
		return light == null ? DEFAULT_SHADOW_INTENSITY : light.getShadowIntensity();
	}

	public double getLightIntensity(Position p) {
		LightPosition light = getLight(p);
		return light == null ? DEFAULT_LIGHT_INTENSITY : light.getLightIntensity();
	}

	private double mergeShadow(double intensity, double intensity2) {
		return Math.max(intensity, intensity2);
	}

	private double mergeLight(double intensity, double intensity2) {
		return Math.min(1.0, intensity + intensity2);
	}

	private LightPosition getLight(Position position) {
		for (LightPosition lp : lightMask) {
			if (lp.equals(position)) {
				return lp;
			}
		}
		return null;
	}

	@Override
	public void update(final GameContext context) {
		reset();
		
		final List<LightSource> lightSources = context.getGameObjects(LightSource.class);

		for (LightSource source : lightSources) {
			final Collection<LightPosition> lights = source.getLights();

			for (LightPosition position : lights) {
				light(position);
			}
		}
	}

	@Override
	public GId id() {
		return gId;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "#" + gId;
	}
}
