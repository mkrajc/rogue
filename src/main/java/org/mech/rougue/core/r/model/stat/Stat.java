package org.mech.rougue.core.r.model.stat;

import java.io.Serializable;

public interface Stat<T> extends Serializable{
	String getKey();
	T getValue();
	void setValue(T newVal);
}
