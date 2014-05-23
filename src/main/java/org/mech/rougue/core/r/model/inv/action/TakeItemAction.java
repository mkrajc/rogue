package org.mech.rougue.core.r.model.inv.action;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.action.object.ObjectAction;
import org.mech.rougue.core.r.model.inv.ItemMapObject;

public class TakeItemAction implements ObjectAction {
	
	private final ItemMapObject itemMapObject;
	private final GameContext ctx;
	
	public TakeItemAction(final ItemMapObject item, final GameContext ctx) {
		this.itemMapObject = item;
		this.ctx = ctx;
	}

	@Override
	public String getActionName() {
		return "i_take_item";
	}

	@Override
	public void invoke() {
		ctx.getData().getPlayer().inventory.take(itemMapObject.getItem());
		// remove wrapped item from map
		ctx.remove(itemMapObject);
		
	}

	@Override
	public boolean enabled() {
		return true;
	}

	@Override
	public String getObjectName() {
		return itemMapObject.getItem().getName();
	}
	
}
