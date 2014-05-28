package org.mech.rougue.core.r.model.player.stat;

import java.io.Serializable;
import org.mech.rougue.core.r.model.combat.CombatantStats;

public class PlayerStats implements Serializable {
	private static final long serialVersionUID = 8568136273592980186L;
	
	public CombatantStats combatantStats = new CombatantStats();

}
