package org.mech.rougue.core.factory;

import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.r.handler.game.light.LightMask;
import org.mech.rougue.core.r.handler.game.player.PlayerSight;
import org.mech.rougue.factory.AbstractDefinition;

public class UpdateAwareHandlersDefinition extends AbstractDefinition {
	
	@Override
	public void definitions() {
		singleton(Player.class);
		singleton(PlayerSight.class);
		singleton(LightMask.class);
	}
}
