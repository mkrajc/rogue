package org.mech.rougue.core.r.model.stat;

public interface Stat<T> {
	String getKey();
	T getValue();
	void setValue(T newVal);
}
