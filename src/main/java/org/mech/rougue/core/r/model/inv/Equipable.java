package org.mech.rougue.core.r.model.inv;

import org.mech.rougue.core.game.GameContext;

public interface Equipable extends Item {
	EquipmentType getEquipmentType();
	boolean isEquipped();
	int slots();
	
	void onEquip(GameContext ctx);
	void onUnequip(GameContext ctx);
}
