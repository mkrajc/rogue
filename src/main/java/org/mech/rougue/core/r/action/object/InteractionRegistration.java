package org.mech.rougue.core.r.action.object;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.event.InteractionEvent;
import org.mech.rougue.core.r.handler.register.Registration;

public class InteractionRegistration implements Registration {

	private boolean registered = false;
	private final GameContext context;
	private final InteractiveObject object;
	

	public InteractionRegistration(final GameContext context, final InteractiveObject object) {
		super();
		this.context = context;
		this.object = object;
	}

	@Override
	public void register() {
		registered = true;
		context.eventBus.fire(new InteractionEvent(object, true));
	}

	@Override
	public void unregister() {
		registered = false;
		context.eventBus.fire(new InteractionEvent(object, false));
	}

	@Override
	public void destroy() {
		unregister();
	}

	@Override
	public boolean isRegistered() {
		return registered;
	}

}
