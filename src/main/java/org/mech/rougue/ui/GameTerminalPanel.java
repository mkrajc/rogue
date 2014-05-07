package org.mech.rougue.ui;

import org.mech.rougue.timer.TickHandler;
import org.mech.rougue.timer.TickTimer;
import org.mech.terminator.swing.TerminalPanel;

public class GameTerminalPanel extends TerminalPanel {

	private static final long serialVersionUID = -5797605225663347969L;

	private TickTimer tickTimer;

	public GameTerminalPanel() {
		final GameTerminalPanel gp = this;
		tickTimer = new TickTimer("render", new TickHandler() {

			@Override
			public void onTick(double delta) {
				gp.repaint();
			}
		}, 35);
	}

	public void autoRender() {
		tickTimer.start();
	}

}
