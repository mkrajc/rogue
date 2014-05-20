package org.mech.rougue.core.r.model.combat;

import org.mech.rougue.core.r.model.combat.dmg.Damage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Combatant {
	private final static Logger LOG = LoggerFactory.getLogger(Combatant.class);

	private IsCombatant combatant;
	
	public Combatant(final IsCombatant combatStats) {
		this.combatant = combatStats;
	}

	public int takeDamage(final Damage damage) {
		final int taken = damage.taken(this);
		LOG.debug("[" + combatant + "] takes damage " + taken + "(" + damage + ")");
		getStats().getHitPoints().decrease(taken);
		return taken;
	}

	public CombatantStats getStats() {
		return combatant.getCombatStats();
	}

}