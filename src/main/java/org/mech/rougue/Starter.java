package org.mech.rougue;

import org.mech.rougue.core.engine.Engine;
import org.mech.rougue.core.factory.GameFactory;
import org.mech.rougue.factory.Factory;
import org.mech.rougue.factory.Inject;
import org.mech.rougue.ui.GameFrame;
import org.mech.rougue.ui.GameTerminalPanel;
import org.mech.terminator.Terminal;
import org.mech.terminator.TerminalSize;

public class Starter {

	@Inject
	private Engine engine;

	public static void main(String[] args) {
		final Factory coreFactory = new GameFactory();
		coreFactory.initialize();

		Terminal.INSTANCE = new Terminal(new TerminalSize(30, 100));

		GameFrame window = new GameFrame();
		GameTerminalPanel panel = new GameTerminalPanel();

		window.setTerminalPanel(panel);
		window.showWindow();

		final Starter starter = Factory.getBean(Starter.class);
		starter.onStart();
	}

	public void onStart() {
		engine.start();
	}
}
