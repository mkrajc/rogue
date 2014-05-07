package org.mech.rougue.core.game.start.handler;

import org.mech.rougue.core.game.start.component.StartMenuComponent;
import org.mech.rougue.factory.Inject;
import org.mech.rougue.ui.screen.ScreenRenderHandler;
import org.mech.terminator.component.Label;
import org.mech.terminator.component.panel.HorizontalPanel;
import org.mech.terminator.component.panel.VerticalPanel;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.screen.Screen;

public class StartScreen extends ScreenRenderHandler {

	@Inject
	private StartMenuComponent startComponent;

	@Override
	protected void doInitializeScreen(final Screen screen) {
		final VerticalPanel panel = new VerticalPanel();
		final HorizontalPanel header = new HorizontalPanel();

		header.setPrefferedSize(Dimension.of(0, 2));
		header.addComponent(new Label("Version @r0.0.1"));

		panel.addComponent(header);
		panel.addComponent(startComponent);

		screen.getRoot().addComponent(panel);
	}

	@Override
	protected String getScreenKey() {
		return "startMenu";
	}

	public void onSwitch() {
		startComponent.focus();
	}
}
