package org.mech.rougue.core.r.model.combat;


public class CombatUtils {

	public static int damageTaken(final int damage, final double resistRating){
		// cannot resist more than 80% of damage
		final double reduction = Math.min(1.0 + (resistRating / damage), 5.0);
		final int dmg = (int) (damage / reduction);
		return dmg;
	}
}
