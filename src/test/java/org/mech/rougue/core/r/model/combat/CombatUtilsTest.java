package org.mech.rougue.core.r.model.combat;

import static org.junit.Assert.*;
import org.junit.Test;

public class CombatUtilsTest {

	@Test
	public void testDamageTaken() {
		assertEquals(100, CombatUtils.damageTaken(100, 0.0));
		assertEquals(50, CombatUtils.damageTaken(100, 100.0));
		assertEquals(20, CombatUtils.damageTaken(100, 1000.0));
		assertEquals(20, CombatUtils.damageTaken(100, Double.MAX_VALUE));
	}

}
