package org.mech.rougue.core.game.play.action.game;

import org.mech.rougue.core.game.play.action.DefaultAction;
import org.mech.rougue.core.game.play.component.dialog.PauseDialog;
import org.mech.rougue.core.game.state.GameState;
import org.mech.rougue.factory.Inject;

public class PauseAction extends DefaultAction {

	@Inject
	private GameState gameState;

	@Inject
	private PauseDialog dialog;

	@Override
	protected void invoke() {
		doInvoke();
	}

	@Override
	protected void doInvoke() {
		boolean paused = !gameState.isPaused();
		gameState.setPaused(paused);
		dialog.setVisible(paused);
	}

	@Override
	public String getActionType() {
		return "game_pause";
	}

}
