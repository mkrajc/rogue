package org.mech.rougue.core.r.model.trap;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.model.combat.dmg.Damage;

public class DamageTrap extends Trap {
	
	private Damage damage;
	
	public DamageTrap(final Damage damage) {
		this.damage = damage;
	}

	@Override
	protected void doActivateTrap(final GameContext context) {
		// TODO chance to avoid trap
		context.getData().getPlayer().combatant.takeDamage(damage);

	}
}
