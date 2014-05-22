package org.mech.rougue.core.r.model.inv;

public interface Equipable extends Item {
	EquipmentType getEquipmentType();
	boolean isEquipped();
}
