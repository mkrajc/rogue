package org.mech.rougue.core.game.start.action;

import org.mech.rougue.core.engine.Engine;
import org.mech.rougue.core.game.GameLoader;
import org.mech.rougue.factory.Inject;

public class StartGameAction  {

	@Inject
	private GameLoader gLoader;

	@Inject
	private Engine engine;

	public void onAction() {
		gLoader.load();
		engine.start();
	}

}
