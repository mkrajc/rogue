package org.mech.rougue.core.r.model.stat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StatImpl<T> implements Stat<T> {

	private final static Logger LOG = LoggerFactory.getLogger(IntegerStat.class);

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
		LOG.debug(this + " set " + newVal);
	}
	
	@Override
	public String toString() {
		return key + "=" + stat;
	}
}
