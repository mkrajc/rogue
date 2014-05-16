package org.mech.rougue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.mech.rougue.core.factory.GameFactory;
import org.mech.rougue.factory.Factory;
import org.mech.rougue.factory.Inject;
import org.mech.rougue.ui.GameFrame;
import org.mech.terminator.Terminal;
import org.mech.terminator.TerminalSize;

public class Starter {

	@Inject
	private GameFrame gameWindow;

	public static void main(final String[] args) {
		try {
			// Set cross-platform Java L&F (also called "Metal")
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (final UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (final ClassNotFoundException e) {
			// handle exception
		} catch (final InstantiationException e) {
			// handle exception
		} catch (final IllegalAccessException e) {
			// handle exception
		}

		final Factory coreFactory = new GameFactory();
		coreFactory.initialize();

		Terminal.setInstance(new Terminal(new TerminalSize(30, 100)));

		final Starter starter = Factory.getBean(Starter.class);
		starter.onStart();
	}

	public void onStart() {
		gameWindow.showWindow();
	}
}
