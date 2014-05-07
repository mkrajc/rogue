package org.mech.rougue.core.game.model.trap;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.area.AreaAwareUpdateHandler;
import org.mech.rougue.core.game.model.map.render.MapObject;

public interface Trap extends MapObject, AreaAwareUpdateHandler {
	
	TrapEffect getEffect();
	
	boolean isInvoked(GameContext context);
	
	boolean isTrapActivated();
	
}
