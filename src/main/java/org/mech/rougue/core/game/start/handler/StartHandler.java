package org.mech.rougue.core.game.start.handler;

import javax.annotation.PostConstruct;
import org.mech.rougue.core.engine.handler.HandlerWrapper;
import org.mech.rougue.core.engine.handler.input.InputHandler;
import org.mech.rougue.core.engine.handler.render.RenderHandler;
import org.mech.rougue.core.engine.handler.update.NullUpdateHandler;
import org.mech.rougue.core.engine.handler.update.UpdateHandler;
import org.mech.rougue.core.game.start.menu.StartMenu;
import org.mech.rougue.factory.Inject;

public class StartHandler extends HandlerWrapper {

	@Inject
	private StartScreen startRenderer;

	@Inject
	private StartMenu menu;

	@PostConstruct
	public void init() {
		_switch_();
	}

	@Override
	protected InputHandler getInputHandler() {
		return menu;
	}

	@Override
	protected RenderHandler getRenderHandler() {
		return startRenderer;
	}

	@Override
	protected UpdateHandler getUpdateHandler() {
		return NullUpdateHandler.INSTANCE;
	}

	@Override
	public void _switch_() {
		super._switch_();
		startRenderer.onSwitch();
	}

}
