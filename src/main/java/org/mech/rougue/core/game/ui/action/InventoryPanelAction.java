package org.mech.rougue.core.game.ui.action;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.play.action.DefaultAction;
import org.mech.rougue.core.game.ui.InventoryPanel;
import org.mech.rougue.factory.Inject;
import org.mech.rougue.ui.GameFrame;

public class InventoryPanelAction extends DefaultAction {

	@Inject
	private GameFrame panel;

	@Inject
	private GameContext context;

	@Inject
	private InventoryPanel inventoryPanel;

	@Override
	protected void doInvoke() {
		inventoryPanel.setInventory(context.getData().getPlayer().inventory);
		inventoryPanel.showInPanel(panel.getLeftPanel());
	}

	@Override
	public String getActionType() {
		return "show_inventory_panel";
	}

}
