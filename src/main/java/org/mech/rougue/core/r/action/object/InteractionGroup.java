package org.mech.rougue.core.r.action.object;

import java.util.List;

public class InteractionGroup {
	ObjectActionDispatcher dispatcher = new ObjectActionDispatcher();

	public boolean isGroup() {
		return dispatcher.getList().size() > 1;
	}

	public List<ObjectAction> getInteractions() {
		return dispatcher.getList();
	}
	
	public ObjectAction getGroupAction(){
		return dispatcher;
	}
}
