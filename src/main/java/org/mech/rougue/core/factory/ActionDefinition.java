package org.mech.rougue.core.factory;

import org.mech.rougue.core.game.play.action.ActionDispatcher;
import org.mech.rougue.core.game.play.action.ActionMapping;
import org.mech.rougue.core.game.play.action.game.PauseAction;
import org.mech.rougue.core.game.play.action.game.SeeMapAction;
import org.mech.rougue.core.game.play.action.move.MoveEastAction;
import org.mech.rougue.core.game.play.action.move.MoveNorthAction;
import org.mech.rougue.core.game.play.action.move.MoveSouthAction;
import org.mech.rougue.core.game.play.action.move.MoveWestAction;
import org.mech.rougue.core.game.play.action.turn.TurnFreezeAction;
import org.mech.rougue.core.game.ui.action.PlayerStatsPanelAction;
import org.mech.rougue.core.r.model.door.action.CloseDoorAction;
import org.mech.rougue.core.r.model.door.action.DoorActions;
import org.mech.rougue.core.r.model.door.action.OpenDoorAction;
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

		singleton(TurnFreezeAction.class);
		
//		singleton(DoorAction.class);

		singleton(PlayerStatsPanelAction.class);
		
		singleton(SeeMapAction.class);
		
		// object actions
		singleton(DoorActions.class);
		singleton(CloseDoorAction.class);
		singleton(OpenDoorAction.class);
	}

}
