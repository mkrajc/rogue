package org.mech.rougue.core.r.model.light;

import java.io.Serializable;

import org.mech.terminator.geometry.Position;

public class Light implements Serializable {
    private Position pos;

    public Light(Position position) {
        this.pos = Position.clone(position);
    }

    private double lightIntensity;
    private double shadowIntensity;

    public Position getPosition(){
        return pos;
    }

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

    public static Light clone(Light p) {
        Light lp = new Light(p.pos);
        lp.lightIntensity = p.lightIntensity;
        lp.shadowIntensity = p.shadowIntensity;
        return lp;
    }

}
