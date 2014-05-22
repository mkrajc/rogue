package org.mech.rougue.core.r.model.inv;

public enum EquipmentType {
	HEAD(1), CHEST(1), LEGS(1), FOOT(1), FINGER(2), NECK(2), HAND(2);

	private final int slots;
	private EquipmentType(final int slots) {
		this.slots = slots;
	}
	public int getSlots() {
		return slots;
	}
}
