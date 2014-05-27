package org.mech.rougue.core.r.model.inv.item.weapon;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.model.combat.attack.Attacks;
import org.mech.rougue.core.r.model.combat.dmg.CanDoDamage;
import org.mech.rougue.core.r.model.combat.dmg.MetaDamage;
import org.mech.rougue.core.r.model.inv.Equipment;
import org.mech.rougue.core.r.model.inv.EquipmentType;
import org.mech.rougue.core.r.model.inv.ItemType;
import org.mech.rougue.core.r.model.inv.item.AbstractEquipableItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Weapon extends AbstractEquipableItem implements CanDoDamage {

	private final static Logger LOG = LoggerFactory.getLogger(Weapon.class);

	protected MetaDamage metaDamage;
	protected WeaponType weaponType;
	protected final WeaponHandType handType;

	public Weapon(final WeaponHandType handType) {
		// weapons are hand only
		this.type = ItemType.WEAPON;
		this.equipmentType = EquipmentType.HAND;
		this.metaDamage = new MetaDamage();
		this.handType = handType;
	}

	public WeaponHandType getHandType() {
		return handType;
	}

	@Override
	public int slots() {
		return handType.ordinal() + 1;
	}

	@Override
	public MetaDamage getMetaDamage() {
		return metaDamage;
	}

	@Override
	public final void onEquip(final GameContext ctx) {
		equip(ctx, this);
		Attacks.onWeaponEquipChanged(ctx.getData().getPlayer(), ctx.getData().getPlayer().equipment);
		// super called as last
		super.onEquip(ctx);
		LOG.info("Weapon Equipped " + this);
	}

	@Override
	public void onUnequip(final GameContext ctx) {
		final Equipment equipment = ctx.getData().getPlayer().equipment;

		if (equipment.getRightHandWeapon() != null && equals(equipment.getRightHandWeapon())) {
			equipment.setRightHandWeapon(null);
		}

		if (equipment.getLeftHandWeapon() != null && equals(equipment.getLeftHandWeapon())) {
			equipment.setLeftHandWeapon(null);
		}

		Attacks.onWeaponEquipChanged(ctx.getData().getPlayer(), equipment);
		super.onUnequip(ctx);
		LOG.info("Weapon Unequipped " + this);
	}

	@Override
	public String getRenderType() {
		return super.getRenderType() + "." + weaponType.name().toLowerCase();
	}

	public boolean isOneHanded() {
		return WeaponHandType.ONE_HANDED.equals(getHandType());
	}

	public boolean isTwoHanded() {
		return WeaponHandType.TWO_HANDED.equals(getHandType());
	}

	protected abstract void equip(final GameContext ctx, final Weapon weapon);

	public WeaponType getWeaponType() {
		return weaponType;
	}

	public void setWeaponType(final WeaponType weaponType) {
		this.weaponType = weaponType;
	}

	public void setMetaDamage(final MetaDamage metaDamage) {
		this.metaDamage = metaDamage;
	}

}
