package org.mech.rougue.core.config.ui;

import java.awt.Color;

public class ColorConfig {
	private int[] rgb = new int[] { 0, 0, 0 };
	private int[] colorShift = new int[] { 0, 0, 0 };

	public ColorConfig(int r, int g, int b) {
		rgb[0] = r;
		rgb[1] = g;
		rgb[2] = b;
	}

	public int[] getRgb() {
		return rgb;
	}

	public void setRgb(int[] rgb) {
		this.rgb = rgb;
	}

	public int[] getColorShift() {
		return colorShift;
	}

	public void setColorShift(int[] colorShift) {
		this.colorShift = colorShift;
	}

	public Color toColor() {
		return new Color(rgb[0], rgb[1], rgb[2]);
	}

}
