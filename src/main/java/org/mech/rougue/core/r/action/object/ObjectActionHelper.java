package org.mech.rougue.core.r.action.object;

import java.util.ArrayList;
import java.util.List;
import org.mech.rougue.core.game.play.action.Action;
import org.mech.rougue.core.r.GObject;

public class ObjectActionHelper {

	public static <T extends GObject, A extends ObjectAction<T>> List<A> build(T object, List<A> actions) {
		List<A> list = new ArrayList<A>();
		for (A action : actions) {
			if (action.enabled(object)) {
				list.add(action);
			}
		}
		return list;
	}

	public static <T extends GObject, A extends ObjectAction<T>> List<Action> toInputActions(T object, List<A> objectActions) {
		List<Action> list = new ArrayList<Action>();
		for (A action : objectActions) {
			list.add(new ObjectActionToActionAdapter(action, object));
		}
		return list;
	}
}
