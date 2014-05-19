package org.mech.rougue.ui;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import org.mech.rougue.core.engine.Engine;
import org.mech.rougue.core.game.play.handler.GameInput;
import org.mech.rougue.factory.Inject;
import org.mech.rougue.timer.TickHandler;
import org.mech.rougue.timer.TickTimer;
import org.mech.terminator.swing.TerminalPanel;

public class GameTerminalPanel extends TerminalPanel implements FocusListener {

	private static final long serialVersionUID = -5797605225663347969L;

	private final TickTimer renderTimer;
	
	@Inject
	private GameInput gameInput;
	
	@Inject
	private Engine engine;

	public GameTerminalPanel() {
		addFocusListener(this);
		setSquareTerminal(true);
		final GameTerminalPanel gp = this;
		renderTimer = new TickTimer("render", new TickHandler() {

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
		renderTimer.start();
	}

	@Override
	public void focusGained(final FocusEvent event) {
		engine.resume();
	}

	@Override
	public void focusLost(final FocusEvent event) {
		engine.pause();
	}

}
