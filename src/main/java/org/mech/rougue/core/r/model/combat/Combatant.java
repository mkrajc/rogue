package org.mech.rougue.core.r.model.combat;

import org.mech.rougue.core.r.model.combat.dmg.Damage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Combatant {
	private final static Logger LOG = LoggerFactory.getLogger(Combatant.class);

	private CombatantStats stats;

	public int takeDamage(final Damage damage) {
		final int taken = damage.taken(this);
		LOG.debug("Combatant takes damage " + taken + "(" + damage + ")");
		stats.getHitPoints().decrease(taken);
		return taken;
	}

	public CombatantStats getStats() {
		return stats;
	}

	public void setStats(final CombatantStats stats) {
		this.stats = stats;
	}

}
