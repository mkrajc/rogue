package org.mech.rougue.core.r.model.stat;

public class StatImpl<T> implements Stat<T> {
	T stat;
	String key;
	StatGroup group;

	@Override
	public String getKey() {
		final String gKey = group.getKey();
		return (gKey == null ? "" : (gKey + ".")) + key;
	}

	@Override
	public T getValue() {
		return stat;
	}

	@Override
	public void setValue(final T newVal) {
		this.stat = newVal;
	}
	
}
