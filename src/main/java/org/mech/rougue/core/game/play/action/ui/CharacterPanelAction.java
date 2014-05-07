package org.mech.rougue.core.game.play.action.ui;

import org.mech.rougue.core.game.play.component.panel.AcionPanel;
import org.mech.rougue.core.game.play.component.panel.CharacterPanelComponent;
import org.mech.rougue.factory.Inject;

public class CharacterPanelAction extends ActionPanelAction {

	@Inject
	private CharacterPanelComponent panel;

	@Override
	public String getActionType() {
		return "show_character_panel";
	}

	@Override
	protected AcionPanel getPanel() {
		return panel;
	}

}
