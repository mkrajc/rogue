package org.mech.rougue.core.game.model.trap;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.player.Player;

public abstract class PlayerTrapEffect implements TrapEffect {

	@Override
	public void update(GameContext context) {
		fire(context.getData().getPlayer());
	}

	protected abstract void fire(Player player);

}
