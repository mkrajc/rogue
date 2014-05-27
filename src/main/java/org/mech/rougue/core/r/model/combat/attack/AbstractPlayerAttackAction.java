package org.mech.rougue.core.r.model.combat.attack;

import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.r.model.combat.Combat;
import org.mech.rougue.core.r.model.combat.Combatant;

public class AbstractPlayerAttackAction extends AbstractAttackAction {

	protected Player player;

	public AbstractPlayerAttackAction(final Player player) {
		this.player = player;
	}

	@Override
	protected Combatant getTarget(final Combat combat) {
		Combatant target = validate(combat.getMarkedTarget());

		if (target == null) {
			target = validate(combat.getClosestTarget(player.getPosition()));

			if (target != null) {
				combat.markTarget(target);
			}
		}

		return target;
	}

	private Combatant validate(final Combatant target) {
		if (target != null && isInAttackRange(target)) {
			return target;
		}
		return null;
	}

	private boolean isInAttackRange(final Combatant markedTarget) {
		// TODO
		return true;
	}

	@Override
	protected void hitTarget(final Combatant attacker, final Combatant target) {
		System.out.println("TODO hit target");
	}

	@Override
	protected Combatant getAttacker(final Combat combat) {
		return player.combatant;
	}

}
