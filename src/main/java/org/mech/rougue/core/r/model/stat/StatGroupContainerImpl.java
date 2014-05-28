package org.mech.rougue.core.r.model.stat;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class StatGroupContainerImpl implements StatGroupContainer {
	private static final long serialVersionUID = -4875802698254366453L;
	protected Map<String, StatGroup> groupMap = new LinkedHashMap<String, StatGroup>();

	@Override
	public Collection<StatGroup> getGroups() {
		return groupMap.values();
	}

	@Override
	public StatGroup get(final String key) {
		return groupMap.get(key);
	}

	public void addGroup(final StatGroup group) {
		groupMap.put(group.getKey(), group);
	}

	public StatGroup createGroup(final String key) {
		final StatGroupImpl groupImpl = new StatGroupImpl();
		groupImpl.key = key;

		groupMap.put(key, groupImpl);
		return groupImpl;
	}

}
