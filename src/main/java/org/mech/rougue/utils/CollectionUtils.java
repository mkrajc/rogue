package org.mech.rougue.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionUtils {

	public interface SelfPopulate<K, V> {
		V create(K key);
	}

	public static <T> boolean isNotEmpty(final Collection<T> collection) {
		return collection != null && !collection.isEmpty();
	}

	public static <T> List<T> asList(final T... objects) {
		return Arrays.asList(objects);
	}

	public static <T> List<T> flatten(final Collection<? extends Collection<T>> listOfLists) {
		final List<T> list = new ArrayList<T>();
		for (final Collection<T> collection : listOfLists) {
			list.addAll(collection);
		}

		return list;
	}

	public static <T> Map<?, T> getDefaultMap(final T defValue) {
		return new DefaultHashMap<Object, T>(defValue);

	}

	public static <K, V> Map<K, V> getSelfPopulatedMap(final SelfPopulate<K, V> populator) {
		return new SelfPopulatedMap<K, V>(populator);
	}

	public static class DefaultHashMap<K, V> extends HashMap<K, V> {
		private static final long serialVersionUID = -6842332339220319064L;
		protected V defaultValue;
		public DefaultHashMap(final V defaultValue) {
			this.defaultValue = defaultValue;
		}
		@Override
		public V get(final Object k) {
			final boolean containsKey = containsKey(k);

			if (!containsKey) {

			}

			return containsKey ? super.get(k) : defaultValue;
		}
	}

	public static class SelfPopulatedMap<K, V> extends HashMap<K, V> {
		private static final long serialVersionUID = -6842332339220319064L;
		protected SelfPopulate<K, V> self;

		public SelfPopulatedMap(final SelfPopulate<K, V> self) {
			this.self = self;
		}
		@Override
		public V get(final Object k) {
			final boolean containsKey = containsKey(k);

			if (!containsKey) {
				final K castedKey = (K) k;
				final V created = self.create(castedKey);
				put(castedKey, created);
			}

			return super.get(k);
		}
	}

}
