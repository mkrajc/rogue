package org.mech.rougue.core.r.object;

public class GIdFactory {

	private static int last = 0;

	public static GId next() {
		return new GId(++last);
	}

}
