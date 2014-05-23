package org.mech.rougue.core.r.action.object;

import java.util.ArrayList;
import java.util.List;

public class ObjectActionDispatcher implements ObjectAction{

	private List<ObjectAction> list = new ArrayList<ObjectAction>();
	private String name;

	@Override
	public void invoke() {
		for (final ObjectAction a : list) {
			a.invoke();
		}
	}
	
	public void add(final ObjectAction action){
		list.add(action);
		name = action.getActionName();
	}
	
	public List<ObjectAction> getList(){
		return list;
	}

	public void remove(final ObjectAction action) {
		list.remove(action);
	}

	@Override
	public String getActionName() {
		return name;
	}

	@Override
	public boolean enabled() {
		return false;
	}

	@Override
	public String getObjectName() {
		return "i_object_action_all";
	}

}
