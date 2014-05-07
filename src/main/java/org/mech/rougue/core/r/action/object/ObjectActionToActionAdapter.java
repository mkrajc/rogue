package org.mech.rougue.core.r.action.object;

import org.mech.rougue.core.game.play.action.Action;
import org.mech.rougue.core.r.GObject;

public class ObjectActionToActionAdapter extends Action {

	private ObjectAction oAction;
	private GObject gObject;

	public ObjectActionToActionAdapter(ObjectAction action, GObject object) {
		this.oAction = action;
		this.gObject = object;
	}

	@Override
	protected void invoke() {
		oAction.invoke(gObject);
	}

	@Override
	public String getActionType() {
		return oAction.getActionName();
	}

	@Override
	public String toString() {
		return oAction + "#" + gObject;
	}

}
