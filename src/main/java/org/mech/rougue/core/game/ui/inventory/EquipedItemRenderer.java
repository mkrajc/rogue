package org.mech.rougue.core.game.ui.inventory;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class EquipedItemRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 7394344523473720124L;
	
	ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("img/equipped.png"));

	@Override
	public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus,
			final int row, final int column) {
		final DefaultTableCellRenderer label = new DefaultTableCellRenderer();
		final JLabel currentLabel = (JLabel) label.getTableCellRendererComponent(table, "", isSelected, hasFocus, row, column);
		
		if ((Boolean) value) {
			currentLabel.setIcon(icon);
		}
		
		return currentLabel;
	}

}
