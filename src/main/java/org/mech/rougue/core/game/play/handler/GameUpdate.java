package org.mech.rougue.core.game.play.handler;

import org.mech.rougue.core.engine.handler.update.UpdateHandler;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.GameData;
import org.mech.rougue.factory.Inject;

public class GameUpdate implements UpdateHandler {

	@Inject
	private GameContext context;

	@Override
	public void update() {
		context.update();
	}

	public GameContext getGameContext() {
		return context;
	}

	public GameData getGame() {
		return context.getData();
	}

}
