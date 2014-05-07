package org.mech.rougue.core.r.model.door.action;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.event.RebuildLightEvent;
import org.mech.rougue.core.r.model.door.Door;
import org.mech.rougue.factory.Inject;

public abstract class AbstractDoorAction implements DoorAction {

	@Inject
	private GameContext context;

	@Override
	public void invoke(final Door door) {
		doInvoke(door);
		context.getData().getMap().handleEnvironmentChange(door);
		new RebuildLightEvent().fire(context);
	}

	protected abstract void doInvoke(Door door);

	@Override
	public String toString() {
		return getActionName();
	}

}
