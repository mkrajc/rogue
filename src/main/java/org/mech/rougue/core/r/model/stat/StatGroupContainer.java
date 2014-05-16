package org.mech.rougue.core.r.model.stat;

import java.util.Collection;

public interface StatGroupContainer {
	Collection<StatGroup> getGroups();
	StatGroup get(String key);
}
