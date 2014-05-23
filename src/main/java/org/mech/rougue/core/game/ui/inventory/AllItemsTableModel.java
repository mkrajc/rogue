package org.mech.rougue.core.game.ui.inventory;

import java.text.DecimalFormat;
import javax.swing.table.AbstractTableModel;
import org.mech.rougue.core.r.model.inv.Equipable;
import org.mech.rougue.core.r.model.inv.Inventory;
import org.mech.rougue.core.r.model.inv.Item;
import org.mech.rougue.lang.LocalizedResourceBundle;

public class AllItemsTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -6177303710744412444L;
	private String[] columnNames = new String[] { "inv.item.equip", "inv.item.name", "inv.item.type", "inv.item.weight" };
	private Inventory inv;
	private LocalizedResourceBundle bundle;
	private DecimalFormat weightFormat = new DecimalFormat("#.#");

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
		final Item item = inv.getItem(rowIndex);
		if (item != null) {
			if (columnIndex == 0) {
				if (item instanceof Equipable) {
					return ((Equipable) item).isEquipped();
				}
				return false;
			}
			if (columnIndex == 1) {
				return item.getName();
			}
			if (columnIndex == 2) {
				return bundle.getMessage(item.getType().toKey());
			}
			if (columnIndex == 3) {
				return weightFormat.format(item.getWeight());
			}
		}
		return "UNDEF";
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
