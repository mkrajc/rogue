package org.mech.rougue.core.r.handler.register.context;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.handler.register.RegistrationHandler;
import org.mech.rougue.core.r.model.common.GObject;

public class GObjectOnGameContextRegistrationHandler implements RegistrationHandler<GameContext, GObject>{

	@Override
	public void register(GameContext master, GObject object) {
		master.add(object);
	}

	@Override
	public void unregister(GameContext master, GObject object) {
		master.remove(object);
	}



}
