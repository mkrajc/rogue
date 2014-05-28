package org.mech.rougue.core.r.model.stat;

import java.io.Serializable;
import java.util.Collection;

public interface StatGroup extends Serializable{
	String getKey();
	Collection<Stat<?>> getStats();
	Stat<?> get(String key);
}
