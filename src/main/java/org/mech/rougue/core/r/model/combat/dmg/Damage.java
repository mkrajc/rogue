package org.mech.rougue.core.r.model.combat.dmg;

import org.mech.rougue.core.r.model.combat.CombatUtils;
import org.mech.rougue.core.r.model.combat.Combatant;

public abstract class Damage {

	private int value;
	private DamageType type;

	public Damage() {
		super();
	}

	public Damage(final int value, final DamageType type) {
		super();
		this.value = value;
		this.type = type;
	}
	public int getValue() {
		return value;
	}
	public void setValue(final int value) {
		this.value = value;
	}
	public DamageType getType() {
		return type;
	}
	public void setType(final DamageType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type + " " + value;
	}

	public int taken(final Combatant target) {
		return CombatUtils.damageTaken(getValue(), getResistRating(target));
	}

	protected abstract double getResistRating(Combatant target);

}
