package org.mech.rougue.core.r.action.object;

import org.mech.rougue.core.r.model.common.GObject;

public interface ObjectAction<T extends GObject> {
	
	String getActionName();
	
	void invoke(T object);
	
	boolean enabled(T object);
	
}
