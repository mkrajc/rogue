package org.mech.rougue.core.r.model.combat;

import java.util.ArrayList;
import java.util.List;
import org.mech.terminator.geometry.GeometryUtils;
import org.mech.terminator.geometry.Position;

public class Combat {

	protected Combatant markedTarget;

	protected List<Combatant> combatants = new ArrayList<Combatant>();

	public Combatant getMarkedTarget() {
		return markedTarget;
	}

	public Combatant getClosestTarget(final Position position) {
		int minDist = Integer.MAX_VALUE;
		Combatant minDistCombatant = null;

		for (final Combatant combatant : combatants) {
			final int dist = GeometryUtils.dist(position, combatant.getPosition());
			if (dist < minDist) {
				minDist = dist;
				minDistCombatant = combatant;
			}
		}

		return minDistCombatant;
	}

	public void markTarget(final Combatant target) {
		markedTarget = target;
	}

	public void addCombatant(final Combatant combatant) {
		combatants.add(combatant);
	}

	public void removeCombatant(final Combatant combatant) {
		combatants.remove(combatant);
	}
}
