package org.mech.rougue.core.r.handler.register.action;

import org.mech.rougue.core.game.play.action.Action;
import org.mech.rougue.core.game.play.action.ActionDispatcher;
import org.mech.rougue.core.r.handler.register.RegistrationHandler;

public class ActionOnDispatcherRegistrationHandler implements RegistrationHandler<ActionDispatcher, Action> {

	@Override
	public void register(ActionDispatcher master, Action object) {
		master.addAction(object);
	}

	@Override
	public void unregister(ActionDispatcher master, Action object) {
		master.removeAction(object);
	}

}
