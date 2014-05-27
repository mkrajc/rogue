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
		final Combatant markedTarget = combat.getMarkedTarget();

		if (markedTarget != null && isInAttackRange(markedTarget)) {
			return markedTarget;
		} else {
			final Combatant closestTarget = combat.getClosestTarget(player.getPosition());

			if (isInAttackRange(closestTarget)) {
				combat.markTarget(closestTarget);
				return closestTarget;
			}
		}

		return null;
	}

	private boolean isInAttackRange(final Combatant markedTarget) {
		return true;
	}

	@Override
	protected void hitTarget(final Combatant attacker, final Combatant target) {

	}

}
