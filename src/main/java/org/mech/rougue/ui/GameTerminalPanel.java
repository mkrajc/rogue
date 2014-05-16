package org.mech.rougue.ui;

import java.awt.event.KeyEvent;
import org.mech.rougue.core.game.play.handler.GameInput;
import org.mech.rougue.factory.Inject;
import org.mech.rougue.timer.TickHandler;
import org.mech.rougue.timer.TickTimer;
import org.mech.terminator.swing.TerminalPanel;

public class GameTerminalPanel extends TerminalPanel  {

	private static final long serialVersionUID = -5797605225663347969L;

	private final TickTimer tickTimer;
	
	@Inject
	private GameInput gameInput;

	public GameTerminalPanel() {
		setSquareTerminal(true);
		final GameTerminalPanel gp = this;
		tickTimer = new TickTimer("render", new TickHandler() {

			@Override
			public void onTick(final double delta) {
				gp.repaint();
			}
		}, 35);
	}
	
	@Override
	public void dispatchInput(final KeyEvent e) {
		gameInput.queue(e);
	}
	

	public void autoRender() {
		tickTimer.start();
	}

}
