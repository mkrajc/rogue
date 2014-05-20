package org.mech.rougue.core.r.model.combat;

import static org.junit.Assert.*;
import org.junit.Test;
import org.mech.rougue.core.r.model.combat.dmg.PhysicalDamage;

public class CombatantTest {

	@Test
	public void testTakePhysicalDamage() {
		final Combatant combatant = new Combatant();
		final CombatantStats stats = new CombatantStats();
		stats.getHitPoints().setValue(100);
		combatant.setStats(stats);

		assertEquals(20, combatant.takeDamage(new PhysicalDamage(20)));
	}

	@Test
	public void testTakePhysicalDamageArmor() {
		final Combatant combatant = new Combatant();
		final CombatantStats stats = new CombatantStats();
		stats.getHitPoints().setValue(100);
		stats.getArmor().setValue(10);
		combatant.setStats(stats);;
		assertEquals(1, new Integer(20).compareTo(combatant.takeDamage(new PhysicalDamage(20))));
	}

}
