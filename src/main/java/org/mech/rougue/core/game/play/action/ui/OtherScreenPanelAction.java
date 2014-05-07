package org.mech.rougue.core.game.play.action.ui;

import org.mech.rougue.core.game.play.component.panel.AcionPanel;
import org.mech.rougue.core.game.play.component.panel.OtherComponentScreenPanel;
import org.mech.rougue.factory.Inject;

public class OtherScreenPanelAction extends ActionPanelAction {

	@Inject
	private OtherComponentScreenPanel panel;

	@Override
	public String getActionType() {
		return "show_other_panel";
	}

	@Override
	protected AcionPanel getPanel() {
		return panel;
	}

}
