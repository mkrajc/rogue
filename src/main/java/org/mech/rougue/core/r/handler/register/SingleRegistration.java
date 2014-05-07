package org.mech.rougue.core.r.handler.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingleRegistration<M, T> extends AbstractRegistration<M, T> implements Registration {
	private final Logger LOG = LoggerFactory.getLogger(SingleRegistration.class);

	public SingleRegistration(M master, T object, RegistrationHandler<M, T> handler) {
		super(master, object, handler);
	}
	
	@Override
	public void register() {
		if (registered) {
			LOG.warn("object is already registered");
			return;
		}
		super.register();
	}

	@Override
	public void unregister() {
		if (!registered) {
			LOG.warn("object is already unregistered");
			return;
		}
		super.unregister();
	}
	
}
