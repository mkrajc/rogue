package org.mech.rougue.core.r.model.stat;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class StatGroupImpl implements StatGroup {
	private static final long serialVersionUID = -7401472237643111202L;
	String key;
	private final Map<String, Stat<?>> statMap = new LinkedHashMap<String, Stat<?>>();

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public Collection<Stat<?>> getStats() {
		return statMap.values();
	}

	@Override
	public Stat<?> get(final String key) {
		return statMap.get(key);
	}

	public <T> Stat<T> createStat(final String key, final T value, final StatImpl<T> sStat) {
		sStat.group = this;
		sStat.key = key;
		sStat.stat = value;

		statMap.put(key, sStat);
		return sStat;
	}

	public <T> Stat<T> createStat(final String key) {
		return createStat(key, null, new StatImpl<T>());
	}

	public IntegerStat createIntegerStat(final String key) {
		return (IntegerStat) createStat(key, 0, new IntegerStat());
	}

}
