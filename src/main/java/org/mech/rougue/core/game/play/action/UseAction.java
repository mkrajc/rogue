package org.mech.rougue.core.game.play.action;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.event.ShowInteractionEvent;

public class UseAction extends DefaultAction {

    @Override
    protected void doInvoke(GameContext context) {
        context.eventBus.fire(new ShowInteractionEvent());
    }

    @Override
    public String getActionType() {
        return "action_use";
    }

}
