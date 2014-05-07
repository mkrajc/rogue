package org.mech.rougue.playground;

import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.game.model.trap.PlayerTrapEffect;

public class PoisonTrapEffect extends PlayerTrapEffect {
	
	boolean active = true;

	@Override
	protected void fire(Player player) {	
		System.out.println("player take damage ");	
		active = false;
	}

	@Override
	public boolean isActive() {
		return active;
	}

}
