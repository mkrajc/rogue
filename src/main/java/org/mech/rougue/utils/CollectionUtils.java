package org.mech.rougue.utils;

import java.util.Collection;

public class CollectionUtils {

	public static <T> boolean isNotEmpty(Collection<T> collection) {
		return collection != null && !collection.isEmpty();
	}

}
