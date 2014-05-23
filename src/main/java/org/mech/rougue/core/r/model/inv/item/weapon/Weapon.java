package org.mech.rougue.core.r.model.inv.item.weapon;

import org.mech.rougue.core.r.model.inv.EquipmentType;
import org.mech.rougue.core.r.model.inv.item.AbstractEquipableItem;

public class Weapon extends AbstractEquipableItem {
	
	public Weapon() {
		// weapons are hand only
		this.equipmentType = EquipmentType.HAND;
	}

}
