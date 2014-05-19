package org.mech.rougue.core.game.play.action;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.event.ShowInteractionEvent;
import org.mech.rougue.factory.Inject;

public class UseAction extends DefaultAction{
	
	@Inject
	private GameContext context;

	@Override
	protected void doInvoke() {
		context.eventBus.fire(new ShowInteractionEvent());
	}

	@Override
	public String getActionType() {
		return "action_use";
	}

}
