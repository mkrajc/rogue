package org.mech.rougue.ui.color;

import java.awt.Color;

public class ColorUtils {
	/**
	 * Blend two colors.
	 * 
	 * @param color1
	 *            First color to blend.
	 * @param color2
	 *            Second color to blend.
	 * @param ratio
	 *            Blend ratio. 0.5 will give even blend, 1.0 will return color1,
	 *            0.0 will return color2 and so on.
	 * @return Blended color.
	 */
	public static Color blend(Color color1, Color color2, double ratio) {
		float r = (float) ratio;
		float ir = (float) 1.0 - r;

		float rgb1[] = new float[3];
		float rgb2[] = new float[3];

		color1.getColorComponents(rgb1);
		color2.getColorComponents(rgb2);

		Color color = new Color(rgb1[0] * r + rgb2[0] * ir, rgb1[1] * r + rgb2[1] * ir, rgb1[2] * r + rgb2[2] * ir);

		return color;
	}

	/**
	 * Make an even blend between two colors.
	 * 
	 * @param c1
	 *            First color to blend.
	 * @param c2
	 *            Second color to blend.
	 * @return Blended color.
	 */
	public static Color blend(Color color1, Color color2) {
		return blend(color1, color2, 0.5);
	}

	public static Color inverse(Color color) {
		float rgb[] = new float[3];
		color.getColorComponents(rgb);
		return new Color(1.f - rgb[0], 1.f - rgb[1], 1.f - rgb[2]);
	}

}
