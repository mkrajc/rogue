package org.mech.rougue.core.r.action.object;

public interface ObjectAction {
	
	String getActionName();
	String getObjectName();
	
	void invoke();
	
	boolean enabled();
	
}
