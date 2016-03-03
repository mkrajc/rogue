package org.mech.rougue.core.game.ui.action;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.play.action.DefaultAction;
import org.mech.rougue.core.game.ui.PlayerStatsPanel;
import org.mech.rougue.factory.Inject;
import org.mech.rougue.ui.GameFrame;

public class PlayerStatsPanelAction extends DefaultAction {

	@Inject
	private GameFrame panel;

	@Inject
	private PlayerStatsPanel playerStatsPanel;

	@Override
	protected void doInvoke(GameContext context) {
		playerStatsPanel.setPlayerStats(context);
		playerStatsPanel.showInPanel(panel.getRightPanel());
	}

	@Override
	public String getActionType() {
		return "show_character_panel";
	}

}
