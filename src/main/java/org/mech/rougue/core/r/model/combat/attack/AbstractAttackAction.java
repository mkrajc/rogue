package org.mech.rougue.core.r.model.combat.attack;

import org.mech.rougue.core.r.model.combat.Combat;
import org.mech.rougue.core.r.model.combat.Combatant;

public abstract class AbstractAttackAction implements AttackAction {

	@Override
	public void attack(final Combat combat) {
		final Combatant attacker = getAttacker(combat);
		final Combatant target = getTarget(combat);

		attack(attacker, target, combat);
	}

	public void attack(final Combatant attacker, final Combatant target, final Combat combat) {
		if (target != null) {
			final boolean hit = isTargetHit(attacker, target);

			if (hit) {
				hitTarget(attacker, target);
			}
		}
	}

	protected abstract void hitTarget(final Combatant attacker, final Combatant target);

	private boolean isTargetHit(final Combatant attacker, final Combatant target) {
		return true;
	}

	protected abstract Combatant getAttacker(final Combat combat);
	protected abstract Combatant getTarget(final Combat combat);

}
