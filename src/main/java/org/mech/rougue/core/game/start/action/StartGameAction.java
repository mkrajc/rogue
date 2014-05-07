package org.mech.rougue.core.game.start.action;

import org.mech.rougue.core.engine.Engine;
import org.mech.rougue.core.game.play.handler.PlayHandler;
import org.mech.rougue.core.game.state.impl.GameLoader;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.component.MenuModel;

public class StartGameAction implements MenuModel.MenuActionHandler {

	@Inject
	private GameLoader gLoader;

	@Inject
	private PlayHandler playHandler;

	@Inject
	private Engine engine;

	@Override
	public void onAction() {
		gLoader.load();
		playHandler._switch_();
	}

}
