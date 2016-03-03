package org.mech.rougue.core.game.play.action.game;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.map.render.SeenMapRenderer;
import org.mech.rougue.core.game.play.action.DefaultAction;

public class SeeMapAction extends DefaultAction {

	@Override
	protected void doInvoke(GameContext context) {
		context.getState().switchState(SeenMapRenderer.SEE_ALL_SWITCH);
	}

	@Override
	public String getActionType() {
		return "game_see_map";
	}

}
