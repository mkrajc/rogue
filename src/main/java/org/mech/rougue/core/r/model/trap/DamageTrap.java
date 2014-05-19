package org.mech.rougue.core.r.model.trap;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.model.stat.Stat;

public class DamageTrap extends Trap {

	private final int dmg = 10;

	@Override
	protected void doActivateTrap(final GameContext context) {
		final Stat<Integer> hitPoints = context.getData().getPlayer().getStats().getHitPoints();
		hitPoints.setValue(hitPoints.getValue() - dmg);
		
		
	}
}
