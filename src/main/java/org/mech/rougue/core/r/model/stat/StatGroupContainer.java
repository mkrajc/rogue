package org.mech.rougue.core.r.model.stat;

import java.io.Serializable;
import java.util.Collection;

public interface StatGroupContainer extends Serializable{
	Collection<StatGroup> getGroups();
	StatGroup get(String key);
}
