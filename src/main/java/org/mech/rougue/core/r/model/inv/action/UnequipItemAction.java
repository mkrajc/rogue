package org.mech.rougue.core.r.model.inv.action;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.r.action.object.ObjectAction;
import org.mech.rougue.core.r.model.inv.Equipable;

public class UnequipItemAction implements ObjectAction {

	private final Equipable item;
	private final GameContext ctx;

	public UnequipItemAction(final Equipable item, final GameContext ctx) {
		this.item = item;
		this.ctx = ctx;
	}

	@Override
	public String getActionName() {
		return "i_unequip_item";
	}

	@Override
	public void invoke() {
		final Player player = ctx.getData().getPlayer();
		player.equipment.unequip(item, ctx);

		// TODO turn off item
	}

	@Override
	public boolean enabled() {
		return !ctx.getData().getPlayer().equipment.canEquip(item);
	}

	@Override
	public String getObjectName() {
		return item.getName();
	}

}
