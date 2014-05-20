package org.mech.rougue.core.r.model.combat;

import static org.junit.Assert.*;
import org.junit.Test;
import org.mech.rougue.core.r.model.combat.dmg.PhysicalDamage;

public class CombatantTest {

	@Test
	public void testTakePhysicalDamage() {
		assertEquals(20, createCombatant().takeDamage(new PhysicalDamage(20)));
	}

	@Test
	public void testTakePhysicalDamageArmor() {
		final Combatant combatant = createCombatant();
		combatant.getStats().getArmor().setValue(10);
		assertEquals(1, new Integer(20).compareTo(combatant.takeDamage(new PhysicalDamage(20))));
	}

	private Combatant createCombatant() {
		final CombatantStats stats = new CombatantStats();
		stats.getHitPoints().setValue(100);
		final Combatant combatant = new Combatant(new IsCombatant() {

			@Override
			public CombatantStats getCombatStats() {
				return stats;
			}
		});

		return combatant;
	}

}
