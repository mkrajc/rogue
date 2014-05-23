package org.mech.rougue.core.r.model.inv.item;

import org.mech.rougue.core.r.model.inv.Equipable;
import org.mech.rougue.core.r.model.inv.EquipmentType;

public class AbstractEquipableItem extends AbstractItem implements Equipable {
	
	private boolean equipped;
	protected EquipmentType equipmentType;
	protected int slots = 1;

	@Override
	public EquipmentType getEquipmentType() {
		return equipmentType;
	}

	@Override
	public boolean isEquipped() {
		return equipped;
	}

	public void setEquipped(final boolean equipped) {
		this.equipped = equipped;
	}

	@Override
	public int slots() {
		return 1;
	}
	
	

}
