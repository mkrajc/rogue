package org.mech.rougue.utils;

import java.util.Enumeration;
import java.util.Properties;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.Position;

public class PropertyUtils {
	public static String loadOrThrowString(Properties properties, String id) {
		String property = properties.getProperty(id);
		missingProperty(id, property);
		return property;
	}

	private static void missingProperty(String id, String prop) {
		if (prop == null) {
			throw new IllegalArgumentException("Property missing [id=" + id + "]");
		}
	}

	public static String loadString(Properties properties, String id) {
		return properties.getProperty(id);
	}

	public static boolean loadBoolean(Properties properties, String id, boolean defValue) {
		String property = properties.getProperty(id);

		if (property != null) {
			return Boolean.valueOf(property);
		}

		return defValue;
	}

	public static Position loadOrThrowPosition(Properties properties, String id) {
		String property = properties.getProperty(id);
		missingProperty(id, property);
		return parsePosition(property);
	}

	public static Dimension loadOrThrowDimension(Properties properties, String id) {
		String property = properties.getProperty(id);
		missingProperty(id, property);
		return parseDim(property);
	}

	private static Dimension parseDim(String dimString) {
		final String[] split = dimString.split(",");
		return Dimension.of(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
	}

	private static Position parsePosition(String posString) {
		final String[] split = posString.split(",");
		return Position.at(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
	}

	@SuppressWarnings("rawtypes")
	public static Properties matchingSubset(Properties p, String prefix, boolean keepPrefix) {
		Properties result = new Properties();

		if (prefix == null || prefix.length() == 0) {
			return result;
		}

		String prefixMatch, prefixSelf;

		if (prefix.charAt(prefix.length() - 1) != '.') {
			// prefix does not end in a dot
			prefixSelf = prefix;
			prefixMatch = prefix + '.';
		} else {
			// prefix does end in one dot, remove for exact matches
			prefixSelf = prefix.substring(0, prefix.length() - 1);
			prefixMatch = prefix;
		}

		// POSTCONDITION: prefixMatch and prefixSelf are initialized!

		// now add all matches into the resulting properties.
		// Remark 1: #propertyNames() will contain the System properties!
		// Remark 2: We need to give priority to System properties. This is done
		// automatically by calling this class's getProperty method.
		String key;
		for (Enumeration e = p.propertyNames(); e.hasMoreElements();) {
			key = (String) e.nextElement();

			if (keepPrefix) {
				// keep full prefix in result, also copy direct matches
				if (key.startsWith(prefixMatch) || key.equals(prefixSelf)) {
					result.setProperty(key, p.getProperty(key));
				}
			} else {
				// remove full prefix in result, dont copy direct matches
				if (key.startsWith(prefixMatch)) {
					result.setProperty(key.substring(prefixMatch.length()), p.getProperty(key));
				}
			}
		}

		return result;
	}
}
