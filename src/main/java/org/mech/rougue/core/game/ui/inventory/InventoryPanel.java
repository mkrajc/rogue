package org.mech.rougue.core.game.ui.inventory;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.ui.ShowPanel;
import org.mech.rougue.core.r.model.inv.Inventory;
import org.mech.rougue.core.r.model.inv.Item;
import org.mech.rougue.core.r.model.inv.action.DropItemAction;
import org.mech.rougue.factory.Inject;
import org.mech.rougue.lang.LocalizedResourceBundle;

public class InventoryPanel extends ShowPanel {

	private static final long serialVersionUID = 6726987122781268642L;

	@Inject
	private LocalizedResourceBundle bundle;

	@Inject
	private GameContext context;

	private JTable inventoryTable;
	private Inventory inventory;
	private AbstractTableModel inventoryTableModel;

	public InventoryPanel() {
		addFocusListener(this.new Focus());
	}


	public void setInventory(final Inventory inventory) {
		removeAll();

		this.inventory = inventory;
		this.inventoryTableModel = new AllItemsTableModel(inventory, bundle);
		this.inventoryTable = new JTable(inventoryTableModel);

		inventoryTable.setFillsViewportHeight(true);
		inventoryTable.setAutoCreateRowSorter(true);
		inventoryTable.addKeyListener(getEscapeListener());
		inventoryTable.addKeyListener(this.new DropItemKeyListener());

		final JScrollPane scrollPane = new JScrollPane(inventoryTable);
		add(scrollPane);
		add(new JLabel("Total: " + inventory.getTotalWeight()));

	}

	protected void dropSelected() {
		final int[] selectedRows = inventoryTable.getSelectedRows();
		if (selectedRows.length == 0) {
			return;
		} else {
			final List<DropItemAction> actions = new ArrayList<DropItemAction>();
			for (final int index : selectedRows) {
				final Item item = inventory.getItem(index);
				actions.add(new DropItemAction(item, context));
			}

			for (final DropItemAction action : actions) {
				action.invoke();
			}

			inventoryTableModel.fireTableDataChanged();
		}
	}

	class Focus extends FocusAdapter {
		@Override
		public void focusGained(final FocusEvent e) {
			if (inventoryTable != null) {
				inventoryTable.requestFocus();
			}
		}
	}

	class DropItemKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(final KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_D) {
				dropSelected();
			}
		}
	}

}
