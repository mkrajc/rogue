package org.mech.rougue.core.r.model.combat.attack;

import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.r.model.inv.Equipment;

public class Attacks {
	public static AttackAction action;

	public static void onWeaponEquipChanged(final Player player, final Equipment equipment) {

		final boolean rh = equipment.getRightHandWeapon() != null;
		final boolean lh = equipment.getLeftHandWeapon() != null;

		if (rh && lh) {
			if (equipment.getRightHandWeapon().equals(equipment.getLeftHandWeapon())) {
				//Two handed weapon
				Attacks.action = new WeaponAttackAction(player, equipment.getRightHandWeapon());
			} else {
				//TODO dual wield action
			}
		} else if (rh) {
			Attacks.action = new WeaponAttackAction(player, equipment.getRightHandWeapon());
		} else if (lh) {
			Attacks.action = new WeaponAttackAction(player, equipment.getLeftHandWeapon());
		} else {
			Attacks.action = null;
		}

	}
}
