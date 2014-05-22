package org.mech.rougue.core.r.model.inv.action;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.r.action.object.ObjectAction;
import org.mech.rougue.core.r.model.inv.Item;
import org.mech.rougue.core.r.model.inv.ItemMapObject;
import org.mech.terminator.geometry.Position;

public class DropItemAction implements ObjectAction {
	
	private final Item item;
	private final GameContext ctx;
	
	public DropItemAction(final Item item, final GameContext ctx) {
		this.item = item;
		this.ctx = ctx;
	}

	@Override
	public String getActionName() {
		return "i_drop_item";
	}

	@Override
	public void invoke() {
		final Player player = ctx.getData().getPlayer();
		player.inventory.drop(item);
		final ItemMapObject gObject = new ItemMapObject(item);
		gObject.setPosition(Position.clone(player.getPosition()));
		ctx.add(gObject);
		
	}

	@Override
	public boolean enabled() {
		return true;
	}

}
