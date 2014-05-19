package org.mech.rougue.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CollectionUtils {

	public static <T> boolean isNotEmpty(final Collection<T> collection) {
		return collection != null && !collection.isEmpty();
	}

	public static <T> List<T> asList(final T... objects) {
		return Arrays.asList(objects);
	}

}
