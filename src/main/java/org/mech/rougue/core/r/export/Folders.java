package org.mech.rougue.core.r.export;

public class Folders {
	public static final String DEFAULT = "data";
	public static final String SAVE = "save";
	public static final String MAP_DEFAULT_FOLDER = getDefault("/maps/");
	public static final String MAP_FOLDER = getSave("/maps/");
	public static final String PLAYER_DEFAULT_FOLDER = getDefault("/player/");
	public static final String PLAYER_FOLDER = getSave("/player/");

	private static String getDefault(final String s) {
		return DEFAULT + s;
	}

	private static String getSave(final String s) {
		return SAVE + s;
	}
}
