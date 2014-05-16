package org.mech.rougue.core.r.model.stat;

import java.util.Collection;

public interface StatGroup {
	String getKey();
	Collection<Stat<?>> getStats();
	Stat<?> get(String key);
}
