package org.mech.rougue.core.r.model.inv.item;

import org.mech.rougue.core.r.model.inv.EquipmentType;
import org.mech.rougue.core.r.model.inv.ItemType;

public class Jewel extends AbstractEquipableItem {

	public Jewel() {
		this.type = ItemType.JEWEL; 
		this.equipmentType = EquipmentType.NECK;
	}
}
