package org.mech.rougue.core.r.handler.register;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BulkRegistration<M, T> extends AbstractRegistration<M, Collection<T>> implements Registration {

	private Map<T, Registration> partialRegistrations;

	public BulkRegistration(M master, Collection<T> partial, RegistrationHandler<M, T> singleObjectRegistratioHhandler) {
		super(master, partial);
		this.partialRegistrations = new HashMap<T, Registration>(partial.size());
		this.handler = new BulkRegistrationHandler<M, T>(partialRegistrations, singleObjectRegistratioHhandler);
	}

	public Registration get(T object) {
		return partialRegistrations == null ? null : partialRegistrations.get(object);
	}

	@Override
	public void destroy() {
		super.destroy();
		this.partialRegistrations = null;
	}

	public static class BulkRegistrationHandler<M, T> implements RegistrationHandler<M, Collection<T>> {

		private RegistrationHandler<M, T> singleHandler;
		private Map<T, Registration> pRegistrations;

		public BulkRegistrationHandler(Map<T, Registration> pRegistrations, RegistrationHandler<M, T> singleHandle) {
			this.singleHandler = singleHandle;
			this.pRegistrations = pRegistrations;
		}

		@Override
		public void register(M master, Collection<T> object) {
			for (T gObject : object) {
				Registration registration = getRegistrations().get(gObject);

				if (registration == null) {
					registration = new SingleRegistration<M, T>(master, gObject, singleHandler);
					getRegistrations().put(gObject, registration);
				}

				if (!registration.isRegistered()) {
					registration.register();
				}
			}
		}

		@Override
		public void unregister(M master, Collection<T> object) {
			for (T gObject : object) {
				Registration registration = getRegistrations().get(gObject);

				if (registration != null && registration.isRegistered()) {
					registration.unregister();
				}
			}
		}

		public Map<T, Registration> getRegistrations() {
			return pRegistrations;
		}

		public void setRegistrations(Map<T, Registration> pRegistrations) {
			this.pRegistrations = pRegistrations;
		}

	}

}
