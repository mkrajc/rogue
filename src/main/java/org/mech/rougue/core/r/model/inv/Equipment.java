package org.mech.rougue.core.r.model.inv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mech.rougue.core.r.model.inv.item.AbstractEquipableItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Equipment {
	private final static Logger LOG = LoggerFactory.getLogger(Equipment.class);

	private Map<EquipmentType, List<Equipable>> equippedItems = new HashMap<EquipmentType, List<Equipable>>();

	public void equip(final Equipable eq) {
		if (canEquip(eq)) {
			List<Equipable> list = equippedItems.get(eq.getEquipmentType());
			if (list == null) {
				list = new ArrayList<Equipable>();
				equippedItems.put(eq.getEquipmentType(), list);
			}
			list.add(eq);
			if (eq instanceof AbstractEquipableItem) {
				((AbstractEquipableItem) eq).setEquipped(true);
			}
			LOG.info("item equipped [" + eq + "]");
		}
	}

	public void unequip(final Equipable eq) {
		if (eq.isEquipped()) {
			final List<Equipable> list = equippedItems.get(eq.getEquipmentType());
			if (list != null && list.contains(eq)) {
				list.remove(eq);
				if (eq instanceof AbstractEquipableItem) {
					((AbstractEquipableItem) eq).setEquipped(false);
				}
				LOG.info("item unequipped [" + eq + "]");
			}
		}
	}

	public boolean canEquip(final Equipable eq) {
		final List<Equipable> list = equippedItems.get(eq.getEquipmentType());
		final boolean canEquip = list == null ? true : list.size() < eq.getEquipmentType().getSlots();
		return canEquip;
	}
}
