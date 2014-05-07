package org.mech.rougue.core.game.start.action;

import org.mech.terminator.component.MenuModel;

public class StartQuitAction implements MenuModel.MenuActionHandler {

	@Override
	public void onAction() {
		System.exit(0);
	}

}
