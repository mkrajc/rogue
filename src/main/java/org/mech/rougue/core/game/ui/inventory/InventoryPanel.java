package org.mech.rougue.core.game.ui.inventory;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import net.miginfocom.swing.MigLayout;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.ui.ShowPanel;
import org.mech.rougue.core.r.action.object.ObjectAction;
import org.mech.rougue.core.r.model.inv.Equipable;
import org.mech.rougue.core.r.model.inv.Inventory;
import org.mech.rougue.core.r.model.inv.Item;
import org.mech.rougue.core.r.model.inv.action.DropItemAction;
import org.mech.rougue.core.r.model.inv.action.EquipItemAction;
import org.mech.rougue.core.r.model.inv.action.UnequipItemAction;
import org.mech.rougue.factory.Inject;
import org.mech.rougue.lang.LocalizedResourceBundle;

public class InventoryPanel extends ShowPanel {

	private static final long serialVersionUID = 6726987122781268642L;
	private static final String TOTAL_WEIGHT_KEY = "inv.total.weight";

	@Inject
	private LocalizedResourceBundle bundle;

	@Inject
	private GameContext context;

	private JTable inventoryTable;
	private Inventory inventory;
	private AbstractTableModel inventoryTableModel;

	private JLabel totalWeightLabel;
	private JPanel actionPanel = new JPanel(new MigLayout("wrap 2"));

	public InventoryPanel() {
		addFocusListener(this.new Focus());
	}

	public void setInventory(final Inventory inventory) {
		removeAll();

		this.inventory = inventory;
		this.inventoryTableModel = new AllItemsTableModel(inventory, bundle);
		this.inventoryTableModel.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(final TableModelEvent e) {
				totalWeightLabel.setText(createTotalWeightLabel());
				updateActions();
			}
		});
		this.totalWeightLabel = new JLabel(createTotalWeightLabel());
		this.inventoryTable = new JTable(inventoryTableModel);
		this.inventoryTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(final ListSelectionEvent e) {
				updateActions();
			}
		});

		inventoryTable.getColumnModel().getColumn(0).setCellRenderer(new EquipedItemRenderer());

		inventoryTable.setFillsViewportHeight(true);
		inventoryTable.setAutoCreateRowSorter(true);
		inventoryTable.addKeyListener(getEscapeListener());
		inventoryTable.addKeyListener(this.new DropItemKeyListener());
		inventoryTable.addKeyListener(this.new SwapEquipItemKeyListener());

		final JScrollPane scrollPane = new JScrollPane(inventoryTable);

		add(scrollPane, "wrap");
		add(totalWeightLabel, "wrap");
		add(actionPanel);
	}

	private String createTotalWeightLabel() {
		return bundle.getMessage(TOTAL_WEIGHT_KEY, inventory.getTotalWeight());
	}

	protected void updateActions() {
		actionPanel.removeAll();

		final int selCount = inventoryTable.getSelectedRowCount();

		if (selCount == 1) {
			final Item item = getJustSelectedItem();

			final boolean equipped = isEquipped(item);

			if (!equipped) {
				actionPanel.add(new JLabel(bundle.getMessage("inv.action.drop", "D")));
			}

			if (item instanceof Equipable) {
				if (equipped) {
					actionPanel.add(new JLabel(bundle.getMessage("inv.action.unequip", "E")));
				} else {
					actionPanel.add(new JLabel(bundle.getMessage("inv.action.equip", "E")));
				}
			}
		}

		if (selCount > 1) {
			actionPanel.add(new JLabel(bundle.getMessage("inv.action.drop", "D")));
		}

		actionPanel.revalidate();
		actionPanel.repaint();

	}

	protected void dropSelected() {
		final int[] selectedRows = inventoryTable.getSelectedRows();
		if (selectedRows.length > 0) {
			final List<DropItemAction> actions = new ArrayList<DropItemAction>();
			for (final int index : selectedRows) {
				final int modelIndex = inventoryTable.convertRowIndexToModel(index);
				final Item item = inventory.getItem(modelIndex);
				if (!isEquipped(item)) {
					actions.add(new DropItemAction(item, context));
				}
			}

			for (final DropItemAction action : actions) {
				action.invoke();
			}

			preserveSelection();
		}
	}

	protected boolean isEquipped(final Item i) {
		if (i instanceof Equipable) {
			return (((Equipable) i).isEquipped());
		}
		return false;
	}

	protected void preserveSelection() {
		final int selectedRow = inventoryTable.getSelectedRow();
		inventoryTableModel.fireTableDataChanged();
		if (inventoryTable.getRowCount() > selectedRow) {
			inventoryTable.setRowSelectionInterval(selectedRow, selectedRow);
		}
	}

	protected void swapEquipSelected() {
		final Item item = getJustSelectedItem();
		if (item != null) {
			if (item instanceof Equipable) {
				final Equipable eItem = (Equipable) item;
				final ObjectAction action = eItem.isEquipped() ? new UnequipItemAction(eItem, context) : new EquipItemAction(eItem, context);
				action.invoke();

				preserveSelection();
			}
		}
	}

	protected Item getJustSelectedItem() {
		final int[] selectedRows = inventoryTable.getSelectedRows();
		if (selectedRows.length == 1) {
			final int modelIndex = inventoryTable.convertRowIndexToModel(selectedRows[0]);
			return inventory.getItem(modelIndex);
		}
		return null;
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

	class SwapEquipItemKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(final KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_E) {
				swapEquipSelected();
			}
		}
	}

}
