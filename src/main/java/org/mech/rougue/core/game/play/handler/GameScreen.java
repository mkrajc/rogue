package org.mech.rougue.core.game.play.handler;

import org.mech.rougue.core.game.play.component.PlayPanel;
import org.mech.rougue.core.game.play.component.map.MapComponent;
import org.mech.rougue.factory.Inject;
import org.mech.rougue.ui.screen.ScreenRenderHandler;
import org.mech.terminator.screen.Screen;

public class GameScreen extends ScreenRenderHandler {

	@Inject
	private MapComponent gameComponent;

	@Inject
	private PlayPanel playPanel;

	@Override
	protected void doInitializeScreen(Screen screen) {
		screen.getRoot().addComponent(playPanel);
	}

	@Override
	protected String getScreenKey() {
		return "game";
	}

	@Override
	public void onSwitch() {
		gameComponent.focus();
	}

}
