package org.mech.rougue.core.r.model.combat;

import org.mech.rougue.core.r.model.stat.Stat;
import org.mech.rougue.core.r.model.stat.StatGroupContainerImpl;
import org.mech.rougue.core.r.model.stat.StatGroupImpl;

public class CombatantStats extends StatGroupContainerImpl {

	private static final Integer BASE_HP = 100;

	private final Stat<Integer> hitPoints;
	private final Stat<Integer> exp;
	private final Stat<Integer> level;
	private final Stat<Integer> resistPoison;
	private final Stat<Integer> resistMagic;
	private final Stat<Integer> resistShock;

	public CombatantStats() {
		final StatGroupImpl general = createGroup("player.stats.general");
		level = general.createStat("level");
		hitPoints = general.createStat("hp");
		exp = general.createStat("exp");

		final StatGroupImpl resist = createGroup("player.stats.resist");
		resistPoison = resist.createStat("poison");
		resistMagic = resist.createStat("magic");
		resistShock = resist.createStat("shock");

		hitPoints.setValue(BASE_HP);
		exp.setValue(0);
		level.setValue(1);
	}

	@Override
	public StatGroupImpl createGroup(final String key) {
		return (StatGroupImpl) super.createGroup(key);
	}

}
