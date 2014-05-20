package org.mech.rougue.core.r.model.combat;

import org.mech.rougue.core.r.model.stat.IntegerStat;
import org.mech.rougue.core.r.model.stat.StatGroupContainerImpl;
import org.mech.rougue.core.r.model.stat.StatGroupImpl;

public class CombatantStats {

	private static final Integer BASE_HP = 30;

	private StatGroupContainerImpl statGroupContainer;

	private final IntegerStat hitPoints;
	private final IntegerStat exp;
	private final IntegerStat level;
	private final IntegerStat armor;
	
	private final IntegerStat resistPoison;
	private final IntegerStat resistMagic;
	private final IntegerStat resistShock;

	public CombatantStats() {
		statGroupContainer = new StatGroupContainerImpl();
		final StatGroupImpl general = createGroup("player.stats.general");
		level = general.createIntegerStat("level");
		hitPoints = general.createIntegerStat("hp");
		exp = general.createIntegerStat("exp");
		armor = general.createIntegerStat("armor");

		final StatGroupImpl resist = createGroup("player.stats.resist");
		resistPoison = resist.createIntegerStat("poison");
		resistMagic = resist.createIntegerStat("magic");
		resistShock = resist.createIntegerStat("shock");

		hitPoints.setValue(BASE_HP);
		exp.setValue(0);
		level.setValue(1);
	}

	private StatGroupImpl createGroup(final String key) {
		return (StatGroupImpl) statGroupContainer.createGroup(key);
	}

	public IntegerStat getHitPoints() {
		return hitPoints;
	}

	public IntegerStat getExp() {
		return exp;
	}

	public IntegerStat getLevel() {
		return level;
	}

	public IntegerStat getResistPoison() {
		return resistPoison;
	}

	public IntegerStat getResistMagic() {
		return resistMagic;
	}

	public IntegerStat getResistShock() {
		return resistShock;
	}

	public IntegerStat getArmor() {
		return armor;
	}

}
