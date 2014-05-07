package org.mech.rougue.ui;

import org.mech.terminator.swing.TerminalFrame;

public class GameFrame extends TerminalFrame {

	private static final long serialVersionUID = 1L;

	@Override
	public void showWindow() {
		super.showWindow();
		((GameTerminalPanel) getTerminalPanel()).autoRender();
	}

}
