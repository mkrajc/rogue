package org.mech.rougue.core.r.model.combat.dmg;

import org.mech.rougue.core.r.model.combat.Combatant;

public class PhysicalDamage extends Damage {

	public PhysicalDamage() {
		setType(DamageType.PHYSICAL);
	}

	public PhysicalDamage(final int value) {
		this();
		setValue(value);
	}

	@Override
	protected double getResistRating(final Combatant target) {
		final int armorRating = target.getStats().getArmor().getValue();
		return armorRating;
	}

}
