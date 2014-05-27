package org.mech.rougue.core.r.model.combat.attack;

import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.r.model.combat.Combatant;
import org.mech.rougue.core.r.model.combat.dmg.Damage;
import org.mech.rougue.core.r.model.combat.dmg.MetaDamage;
import org.mech.rougue.core.r.model.inv.item.weapon.Weapon;

public class WeaponAttackAction extends AbstractPlayerAttackAction {

	protected Weapon weapon;

	public WeaponAttackAction(final Player player, final Weapon weapon) {
		super(player);
		this.weapon = weapon;
	}

	@Override
	protected void hitTarget(final Combatant attacker, final Combatant target) {
		final MetaDamage metaDamage = weapon.getMetaDamage();

		final MetaDamage finalAttackerDamage = apply(metaDamage);

		for (final Damage d : finalAttackerDamage.getDamages()) {
			target.takeDamage(d);
		}
	}

	private MetaDamage apply(final MetaDamage metaDamage) {
		return metaDamage;
	}

}
