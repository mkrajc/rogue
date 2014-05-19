package org.mech.rougue.core.factory;

import org.mech.rougue.core.game.play.action.ActionDispatcher;
import org.mech.rougue.core.game.play.action.ActionMapping;
import org.mech.rougue.core.game.play.action.UseAction;
import org.mech.rougue.core.game.play.action.game.PauseAction;
import org.mech.rougue.core.game.play.action.game.SeeMapAction;
import org.mech.rougue.core.game.play.action.move.MoveEastAction;
import org.mech.rougue.core.game.play.action.move.MoveNorthAction;
import org.mech.rougue.core.game.play.action.move.MoveSouthAction;
import org.mech.rougue.core.game.play.action.move.MoveWestAction;
import org.mech.rougue.core.game.play.action.turn.TurnFreezeAction;
import org.mech.rougue.core.game.ui.action.InventoryPanelAction;
import org.mech.rougue.core.game.ui.action.PlayerStatsPanelAction;
import org.mech.rougue.core.r.action.object.Interaction;
import org.mech.rougue.factory.AbstractDefinition;

public class ActionDefinition extends AbstractDefinition {

	@Override
	public void definitions() {
		singleton(ActionMapping.class);
		singleton(ActionDispatcher.class);
		singleton(PauseAction.class);
		singleton(MoveEastAction.class);
		singleton(MoveWestAction.class);
		singleton(MoveNorthAction.class);
		singleton(MoveSouthAction.class);
		singleton(UseAction.class);

		singleton(TurnFreezeAction.class);
		
		singleton(Interaction.class);

		singleton(PlayerStatsPanelAction.class);
		singleton(InventoryPanelAction.class);
		singleton(SeeMapAction.class);
		
	}

}
