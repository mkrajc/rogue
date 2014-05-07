package org.mech.rougue.core.r.model.light;

import org.mech.terminator.geometry.Position;

public class LightPosition extends Position {

	public LightPosition(Position position) {
		super(position.x, position.y);
	}

	private double lightIntensity;
	private double shadowIntensity;

	public double getLightIntensity() {
		return lightIntensity;
	}

	public void setLightIntensity(double intensity) {
		this.lightIntensity = intensity;
	}

	public double getShadowIntensity() {
		return shadowIntensity;
	}

	public void setShadowIntensity(double shadowIntensity) {
		this.shadowIntensity = shadowIntensity;
	}

	public static LightPosition clone(LightPosition p) {
		LightPosition lp = new LightPosition(p);
		lp.lightIntensity = p.lightIntensity;
		lp.shadowIntensity = p.shadowIntensity;
		return lp;
	}

}
