package org.mech.rougue.core.r.handler.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractRegistration<M, O> implements Registration {
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	protected O object;
	protected M master;
	protected boolean registered;
	protected RegistrationHandler<M, O> handler;

	public AbstractRegistration(M master, O object) {
		this(master, object, null);
	}

	public AbstractRegistration(M master, O object, RegistrationHandler<M, O> handler) {
		this.object = object;
		this.master = master;
		this.handler = handler;

		if (object == null) {
			throw new IllegalArgumentException("Cannot create registration for null object");
		}

		if (master == null) {
			throw new IllegalArgumentException("Cannot create registration for null master object");
		}
	}

	@Override
	public void register() {
		if (isDestroyed()) {
			logDestroyed();
		} else {
			handler.register(master, object);
			registered = true;
			LOG.debug(getClass().getSimpleName() + " registered [obj=" + object + ", master=" + master + "]");
		}
	}

	@Override
	public void unregister() {
		if (isDestroyed()) {
			logDestroyed();
		} else {
			handler.unregister(master, object);
			registered = false;
			LOG.debug(getClass().getSimpleName() + " unregistered [obj=" + object + ", master=" + master + "]");
		}
	}

	@Override
	public boolean isRegistered() {
		return registered;
	}

	protected void logDestroyed() {
		LOG.warn("object is already destroyed");
	}

	@Override
	public void destroy() {
		unregister();
		this.object = null;
	}

	public boolean isDestroyed() {
		return object == null;
	}

}
