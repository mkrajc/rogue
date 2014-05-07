package org.mech.rougue.core.game.play.action.game;

import org.mech.rougue.core.game.model.map.render.SeenMapRenderer;
import org.mech.rougue.core.game.play.action.DefaultAction;
import org.mech.rougue.core.game.state.GameState;
import org.mech.rougue.factory.Inject;

public class SeeMapAction extends DefaultAction {

	@Inject
	private GameState gameState;

	@Override
	protected void doInvoke() {
		gameState.switchState(SeenMapRenderer.SEE_ALL_SWITCH);
	}

	@Override
	public String getActionType() {
		return "game_see_map";
	}

}
