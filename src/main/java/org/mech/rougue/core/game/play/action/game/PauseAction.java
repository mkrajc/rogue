package org.mech.rougue.core.game.play.action.game;

import javax.swing.JOptionPane;

import org.mech.rogue.game.context.State;
import org.mech.rougue.core.game.play.action.DefaultAction;
import org.mech.rougue.factory.Inject;
import org.mech.rougue.lang.LocalizedResourceBundle;
import org.mech.rougue.ui.GameFrame;

public class PauseAction extends DefaultAction {

	@Inject
	private State gameState;

	@Inject
	private GameFrame gameFrame;

	@Inject
	private LocalizedResourceBundle bundle;

	@Override
	protected void invoke() {
		doInvoke();
	}

	@Override
	protected void doInvoke() {
		gameState.setPaused(true);
		JOptionPane.showMessageDialog(gameFrame, "game paused", "Pause", JOptionPane.INFORMATION_MESSAGE);
		gameState.setPaused(false);
	}

	@Override
	public String getActionType() {
		return "game_pause";
	}

}
