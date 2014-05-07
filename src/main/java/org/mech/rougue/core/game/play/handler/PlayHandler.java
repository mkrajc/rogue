package org.mech.rougue.core.game.play.handler;

import org.mech.rougue.core.engine.handler.HandlerWrapper;
import org.mech.rougue.core.engine.handler.input.InputHandler;
import org.mech.rougue.core.engine.handler.render.RenderHandler;
import org.mech.rougue.core.engine.handler.update.UpdateHandler;
import org.mech.rougue.factory.Inject;

public class PlayHandler extends HandlerWrapper {

	@Inject
	private GameScreen gameScreen;

	@Inject
	private GameInput gameInput;

	@Inject
	private GameUpdate gameUpdate;

	@Override
	public void _switch_() {
		super._switch_();
		gameScreen.onSwitch();
	}

	@Override
	protected RenderHandler getRenderHandler() {
		return gameScreen;
	}

	@Override
	protected InputHandler getInputHandler() {
		return gameInput;
	}

	@Override
	protected UpdateHandler getUpdateHandler() {
		return gameUpdate;
	}

}
