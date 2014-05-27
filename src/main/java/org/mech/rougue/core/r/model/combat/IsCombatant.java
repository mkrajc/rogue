package org.mech.rougue.core.r.model.combat;

import org.mech.rougue.core.r.model.geom.Positionable;

public interface IsCombatant extends Positionable {
	CombatantStats getCombatStats();
}
