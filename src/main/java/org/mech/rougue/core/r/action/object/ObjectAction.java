package org.mech.rougue.core.r.action.object;

public interface ObjectAction {
	
	String getActionName();
	
	void invoke();
	
	boolean enabled();
	
}
