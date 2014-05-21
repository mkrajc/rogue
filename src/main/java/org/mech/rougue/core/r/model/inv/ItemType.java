package org.mech.rougue.core.r.model.inv;

public enum ItemType {
	WEAPON, ARMOR, CLOTHES, JEWEL;

	public String toKey() {
		return "item.type." + name().toLowerCase();
	}
}
