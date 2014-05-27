package org.mech.rougue.core.r.model.combat.attack;

import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.r.model.inv.Equipment;

public class Attacks {
	public static AttackAction action;

	public static void onWeaponEquipChanged(final Player player, final Equipment equipment) {

		final boolean rh = equipment.rightHandWeapon != null;
		final boolean lh = equipment.leftHandWeapon != null;

		if (rh && lh) {
			if (equipment.rightHandWeapon.equals(equipment.leftHandWeapon)) {
				//Two handed weapon
				Attacks.action = new WeaponAttackAction(player, equipment.rightHandWeapon);
			} else {
				//TODO dual wield action
			}
		} else if (rh) {
			Attacks.action = new WeaponAttackAction(player, equipment.rightHandWeapon);
		} else if (lh) {
			Attacks.action = new WeaponAttackAction(player, equipment.leftHandWeapon);
		} else {
			Attacks.action = null;
		}

	}
}
