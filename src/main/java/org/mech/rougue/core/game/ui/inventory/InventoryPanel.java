package org.mech.rougue.core.game.ui.inventory;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.mech.rougue.core.game.ui.ShowPanel;
import org.mech.rougue.core.r.model.inv.Inventory;
import org.mech.rougue.factory.Inject;
import org.mech.rougue.lang.LocalizedResourceBundle;

public class InventoryPanel extends ShowPanel {

	@Inject
	private LocalizedResourceBundle bundle;

	private JTable inventoryTable;

	public void setInventory(final Inventory inventory) {
		removeAll();

		inventoryTable = new JTable(new AllItemsTableModel(inventory, bundle));
		inventoryTable.setFillsViewportHeight(true);
		inventoryTable.setAutoCreateRowSorter(true);
		final JScrollPane scrollPane = new JScrollPane(inventoryTable);
		add(scrollPane);

//		for (final Item item : inventory.getItems()) {
//			final JLabel groupLabel = new JLabel(bundle.getMessage(item.toString()));
//			add(groupLabel, "wrap");
//		}
	}

}
