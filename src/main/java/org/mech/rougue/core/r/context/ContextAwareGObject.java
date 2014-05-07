package org.mech.rougue.core.r.context;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.GObject;

public interface ContextAwareGObject extends GObject {

	void onAdd(GameContext context);
	void onRemove(GameContext context);
}
