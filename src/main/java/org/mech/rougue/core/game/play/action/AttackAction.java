package org.mech.rougue.core.game.play.action;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.model.combat.Combat;
import org.mech.rougue.core.r.model.combat.Combatant;
import org.mech.rougue.core.r.model.combat.CombatantStats;
import org.mech.rougue.core.r.model.combat.IsCombatant;
import org.mech.rougue.core.r.model.combat.attack.Attacks;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.geometry.Position;

public class AttackAction extends DefaultAction {

	@Override
	protected void doInvoke(GameContext context) {
		if(Attacks.action != null){
			System.out.println("TODO action ");
			final Combat combat = new Combat();
			
			combat.addCombatant(new Combatant(new TestCombatant()));
			Attacks.action.attack(combat);
		}
	}

	@Override
	public String getActionType() {
		return "action_attack";
	}
	
	static class TestCombatant implements IsCombatant {
		
		public TestCombatant() {
			this. p = Position.at(5, 4);
			combatantStats = new CombatantStats();
			combatantStats.getHitPoints().setValue(40);
			
		}
		
		private Position p;
		private CombatantStats combatantStats;
		

		@Override
		public Position getPosition() {
			return p;
		}

		@Override
		public void setPosition(final Position position) {
			this.p = position;
		}

		@Override
		public CombatantStats getCombatStats() {
			return combatantStats;
		}
		
	}

}
