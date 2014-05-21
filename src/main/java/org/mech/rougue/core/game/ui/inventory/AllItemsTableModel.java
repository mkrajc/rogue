package org.mech.rougue.core.game.ui.inventory;

import javax.swing.table.AbstractTableModel;
import org.mech.rougue.core.r.model.inv.Inventory;
import org.mech.rougue.core.r.model.inv.Item;
import org.mech.rougue.lang.LocalizedResourceBundle;

public class AllItemsTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -6177303710744412444L;
	private String[] columnNames = new String[] { "inv.item.name", "inv.item.type" };
	private Inventory inv;
	private LocalizedResourceBundle bundle;

	public AllItemsTableModel(final Inventory inventory, final LocalizedResourceBundle bundle) {
		this.inv = inventory;
		this.bundle = bundle;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return inv.getItems().size();
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		final Item item = inv.getItems().get(rowIndex);
		if (columnIndex == 0) {
			return item.getName();
		}
		if (columnIndex == 1) {
			return bundle.getMessage(item.getType().toKey());
		}
		return "";
	}

	@Override
	public String getColumnName(final int column) {
		return bundle.getMessage(columnNames[column]);
	}

	@Override
	public Class getColumnClass(final int c) {
		return getValueAt(0, c).getClass();
	}

}
