package org.mech.rougue.core.r.export;

public class AbstractObjectManipulator {
	public final static String SUFFIX = ".data";

	protected String folder;

	public AbstractObjectManipulator(final String folder) {
		this.folder = folder;
	}

	public String getFilename(final String name) {
		return folder + name + SUFFIX;
	}

}
