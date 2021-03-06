package org.mech.rougue.core.r.model.inv.item.weapon;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.model.inv.Equipment;

public class OneHandedWeapon extends Weapon {

	public OneHandedWeapon() {
		super(WeaponHandType.ONE_HANDED);
	}

	@Override
	protected void equip(final GameContext context, final Weapon weapon) {
		final Equipment equipment = context.getData().getPlayer().equipment;
		if (equipment.getRightHandWeapon() == null) {
			equipment.setRightHandWeapon(weapon);
		} else if (equipment.getLeftHandWeapon() == null) {
			equipment.setLeftHandWeapon(weapon);
		} else {
			throw new IllegalArgumentException("Cannot equip one handed weapon. At least one hand must be free");
		}
	}

}
