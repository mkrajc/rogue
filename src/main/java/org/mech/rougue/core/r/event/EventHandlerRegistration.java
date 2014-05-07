package org.mech.rougue.core.r.event;

import org.mech.rougue.core.r.event.Event.Handler;
import org.mech.rougue.core.r.handler.register.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class EventHandlerRegistration implements Registration {

	protected static final Logger LOG = LoggerFactory.getLogger(EventHandlerRegistration.class);

	private final EventBus owner;
	private final Class<?> clazz;
	private final Handler handler;

	public EventHandlerRegistration(final EventBus bus, final Class<?> clazz, final Handler h) {
		this.owner = bus;
		this.clazz = clazz;
		this.handler = h;
	}

	boolean registered = false;

	@Override
	public void register() {
		if (!registered) {
			registered = true;
			owner.doAdd(clazz, handler);
			LOG.debug("registered handler [h=" + handler + ",clazz=" + clazz.getSimpleName() + "]");
		}
	}

	@Override
	public void unregister() {
		if (registered) {
			registered = false;
			owner.doRemove(clazz, handler);
			LOG.debug("unregistered handler [h=" + handler + ",clazz=" + clazz.getSimpleName() + "]");
		}
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
