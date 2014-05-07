package org.mech.rougue.core.game.start.menu;

import javax.annotation.PostConstruct;
import org.mech.rougue.core.game.start.action.StartGameAction;
import org.mech.rougue.core.game.start.action.StartQuitAction;
import org.mech.rougue.factory.Inject;
import org.mech.rougue.ui.component.Menu;

public class StartMenu extends Menu {
	@Inject
	private StartQuitAction quitAction;

	@Inject
	private StartGameAction playAction;

	@PostConstruct
	public void buildMenu() {
		getModel().add("Start new game", playAction, 's');
		getModel().add("Quit", quitAction);
	}
}
