package org.mech.rougue.core.game.play.handler;

import org.mech.rougue.core.engine.handler.input.InputEvent;
import org.mech.rougue.core.engine.handler.input.InputHandlerAdapter;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.play.action.ActionDispatcher;
import org.mech.rougue.factory.Inject;

public class GameInput extends InputHandlerAdapter {

    @Inject
    private ActionDispatcher actionDispatcher;

    @Override
    public void onInput(InputEvent event, GameContext ctx) {
        actionDispatcher.invokeAction(event, ctx);
    }

}
