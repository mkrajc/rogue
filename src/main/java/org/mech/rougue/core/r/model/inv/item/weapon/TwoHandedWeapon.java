package org.mech.rougue.core.r.model.inv.item.weapon;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.model.inv.Equipment;

public class TwoHandedWeapon extends Weapon {

	public TwoHandedWeapon() {
		super(WeaponHandType.TWO_HANDED);
	}

	@Override
	protected void equip(final GameContext context, final Weapon weapon) {
		final Equipment equipment = context.getData().getPlayer().equipment;
		if (equipment.rightHandWeapon == null && equipment.leftHandWeapon == null) {
			equipment.rightHandWeapon = weapon;
			equipment.leftHandWeapon = weapon;
		} else {
			throw new IllegalArgumentException("Cannot equip two handed weapon if hands are not free");
		}
	}


}
