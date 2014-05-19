package org.mech.rougue.core.game.ui;

import javax.swing.JLabel;
import org.mech.rougue.core.r.model.inv.Inventory;
import org.mech.rougue.core.r.model.inv.Item;
import org.mech.rougue.factory.Inject;
import org.mech.rougue.lang.LocalizedResourceBundle;

public class InventoryPanel extends ShowPanel {
	
	@Inject
	private LocalizedResourceBundle bundle;

	public void setInventory(final Inventory inventory) {
		removeAll();
		
		for (final Item item : inventory.getItems()) {
			final JLabel groupLabel = new JLabel(bundle.getMessage(item.toString()));
//			final Font font = groupLabel.getFont();
//		
//			final Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
//			groupLabel.setFont(boldFont);
			
			add(groupLabel, "wrap");
//			for (final Stat<?> stat : group.getStats()) {
//				add(new JLabel(bundle.getMessage(stat.getKey())), "width 50px");
//				add(new JLabel(stat.getValue() == null ? "" : stat.getValue().toString()), "wrap");
//			}
		}
	}

}
