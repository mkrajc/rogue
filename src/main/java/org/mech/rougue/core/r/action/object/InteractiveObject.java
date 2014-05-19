package org.mech.rougue.core.r.action.object;

import java.util.List;
import org.mech.rougue.core.r.model.common.GObject;

public interface InteractiveObject extends GObject {

	<A extends ObjectAction> List<A> getActions();

}
