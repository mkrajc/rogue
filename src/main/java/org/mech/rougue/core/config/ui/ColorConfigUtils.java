package org.mech.rougue.core.config.ui;

import java.awt.Color;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class ColorConfigUtils {
	public static Color getFixedColor(ColorConfig config) {
		return config == null ? null : new Color(config.getRgb()[0], config.getRgb()[1], config.getRgb()[2]);
	}

	public static Color getShiftColor(ColorConfig config, int line, int column) {
		if (config == null) {
			return null;
		}
		int r = config.getRgb()[0];
		int g = config.getRgb()[1];
		int b = config.getRgb()[2];

		int[] colorShift = config.getColorShift();

		int rShift = r + shift(colorShift[0], line, column);
		int gShift = g + shift(colorShift[1], line, column);
		int bShift = b + shift(colorShift[2], line, column);

		rShift = Math.min(rShift, 255);
		gShift = Math.min(gShift, 255);
		bShift = Math.min(bShift, 255);
		rShift = Math.max(rShift, 0);
		gShift = Math.max(gShift, 0);
		bShift = Math.max(bShift, 0);

		return config == null ? null : new Color(rShift, gShift, bShift);
	}

	private static int shift(int shift, int line, int column) {
		if (shift > 0) {
			return (((line * column * 31) % shift) - (shift / 5) << 1 * 5) % shift;
		}
		return 0;
	}

	public static void main(String[] args) {

		Map<Integer, Integer> counts = new TreeMap<Integer, Integer>();
		for (int i = 1; i < 10 + 1; i++) {
			for (int j = 1; j < 10 + 1; j++) {

				int result = shift(37, i, j);

				if (!counts.containsKey(result)) {
					counts.put(result, 0);
				}
				counts.put(result, counts.get(result) + 1);
				System.out.print(result + " ");
			}
		}

		for (Entry<Integer, Integer> entry : counts.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue() + "%");
		}
	}

	public static Color toGrayScale(Color fgColor) {
		if (fgColor == null) {
			return null;
		}
		int r = fgColor.getRed();
		int g = fgColor.getGreen();
		int b = fgColor.getBlue();

		int avg = (r + g + b) / 3;

		return new Color(avg, avg, avg);

	}

}
