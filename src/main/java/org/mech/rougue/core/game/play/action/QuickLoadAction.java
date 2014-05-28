package org.mech.rougue.core.game.play.action;

import org.mech.rougue.core.game.GameLoader;
import org.mech.rougue.factory.Inject;

public class QuickLoadAction extends DefaultAction {

	@Inject
	private GameLoader loader;

	@Override
	protected void doInvoke() {
			loader.quickload();
	}

	@Override
	public String getActionType() {
		return "quick_load";
	}
}
