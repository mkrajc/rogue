package org.mech.rougue.core.r.model.inv;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.model.combat.dmg.CanDoDamage;
import org.mech.rougue.core.r.model.inv.item.weapon.Weapon;
import org.mech.rougue.utils.CollectionUtils;
import org.mech.rougue.utils.CollectionUtils.SelfPopulate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Equipment {
	private final static Logger LOG = LoggerFactory.getLogger(Equipment.class);

	private Weapon rightHandWeapon;
	private Weapon leftHandWeapon;

	private Map<EquipmentType, List<Equipable>> equippedItems = CollectionUtils
			.getSelfPopulatedMap(new SelfPopulate<EquipmentType, List<Equipable>>() {

				@Override
				public List<Equipable> create(final EquipmentType key) {
					return new ArrayList<Equipable>();
				}

			});

	public void equip(final Equipable eq, final GameContext ctx) {
		if (canEquip(eq)) {
			equippedItems.get(eq.getEquipmentType()).add(eq);
			eq.onEquip(ctx);
			LOG.info("Item equipped [" + eq + "]");
		}
	}

	public void unequip(final Equipable eq, final GameContext ctx) {
		if (eq.isEquipped()) {
			final List<Equipable> list = equippedItems.get(eq.getEquipmentType());
			if (list.contains(eq)) {
				list.remove(eq);
				eq.onUnequip(ctx);
				LOG.info("Item unequipped [" + eq + "]");
			}
		}
	}

	public boolean canEquip(final Equipable eq) {
		final List<Equipable> eqs = equippedItems.get(eq.getEquipmentType());
		int currentSlots = 0;
		for (final Equipable equipable : eqs) {
			currentSlots += equipable.slots();
		}
		final int slotsAfterEquip = currentSlots + eq.slots();
		return slotsAfterEquip <= eq.getEquipmentType().getSlots();
	}

	public List<CanDoDamage> getCanDoDamage() {
		final List<Equipable> values = CollectionUtils.flatten(equippedItems.values());
		final List<CanDoDamage> list = new ArrayList<CanDoDamage>();
		for (final Equipable equipable : values) {
			if (equipable instanceof CanDoDamage) {
				list.add((CanDoDamage) equipable);
			}
		}

		return list;
	}

	public Weapon getRightHandWeapon() {
		return rightHandWeapon;
	}

	public void setRightHandWeapon(final Weapon rightHandWeapon) {
		this.rightHandWeapon = rightHandWeapon;
		if(rightHandWeapon == null){
			LOG.info("Right hand free");
		} else {
			LOG.info("Right hand " + rightHandWeapon);
		}
	}

	public Weapon getLeftHandWeapon() {
		return leftHandWeapon;
	}

	public void setLeftHandWeapon(final Weapon leftHandWeapon) {
		this.leftHandWeapon = leftHandWeapon;
		if(leftHandWeapon == null){
			LOG.info("Left hand free");
		} else {
			LOG.info("Left hand " + leftHandWeapon);
		}
	}

}
