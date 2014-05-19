package org.mech.rougue.core.r.model.door.action;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.action.object.ObjectAction;
import org.mech.rougue.core.r.event.RebuildLightEvent;
import org.mech.rougue.core.r.model.door.Door;

public abstract class AbstractDoorAction implements ObjectAction {

	protected final GameContext ctx;

	protected final Door door;

	public AbstractDoorAction(final GameContext ctx, final Door door) {
		this.door = door;
		this.ctx = ctx;
	}

	@Override
	public void invoke() {
		doInvoke(door);
		ctx.getData().getMap().handleEnvironmentChange(door);
		new RebuildLightEvent().fire(ctx);
	}

	protected abstract void doInvoke(Door door);

	@Override
	public String toString() {
		return getActionName();
	}

}
