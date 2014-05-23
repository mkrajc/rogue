package org.mech.rougue.core.r.model.inv;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.mech.rougue.core.r.model.inv.item.AbstractEquipableItem;
import org.mech.rougue.utils.CollectionUtils;
import org.mech.rougue.utils.CollectionUtils.SelfPopulate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Equipment {
	private final static Logger LOG = LoggerFactory.getLogger(Equipment.class);

	private Map<EquipmentType, List<Equipable>> equippedItems = CollectionUtils
			.getSelfPopulatedMap(new SelfPopulate<EquipmentType, List<Equipable>>() {

				@Override
				public List<Equipable> create(final EquipmentType key) {
					return new ArrayList<Equipable>();
				}

			});

	public void equip(final Equipable eq) {
		if (canEquip(eq)) {
			equippedItems.get(eq.getEquipmentType()).add(eq);
			if (eq instanceof AbstractEquipableItem) {
				((AbstractEquipableItem) eq).setEquipped(true);
			}
			LOG.info("item equipped [" + eq + "]");
		}
	}

	public void unequip(final Equipable eq) {
		if (eq.isEquipped()) {
			final List<Equipable> list = equippedItems.get(eq.getEquipmentType());
			if (list.contains(eq)) {
				list.remove(eq);
				if (eq instanceof AbstractEquipableItem) {
					((AbstractEquipableItem) eq).setEquipped(false);
				}
				LOG.info("item unequipped [" + eq + "]");
			}
		}
	}

	public boolean canEquip(final Equipable eq) {
		final int slotsAfterEquip = equippedItems.get(eq.getEquipmentType()).size() + eq.slots();
		return slotsAfterEquip <= eq.getEquipmentType().getSlots();
	}
}
