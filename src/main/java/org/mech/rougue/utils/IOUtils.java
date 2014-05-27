package org.mech.rougue.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtils {

	public static void closeQuietly(final OutputStream stream) {
		try {
			stream.close();
		} catch (final IOException e) {}
	}

	public static void closeQuietly(final InputStream stream) {
		try {
			stream.close();
		} catch (final IOException e) {}
	}
}
