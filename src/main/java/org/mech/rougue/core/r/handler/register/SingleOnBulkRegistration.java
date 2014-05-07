package org.mech.rougue.core.r.handler.register;

import java.util.Collection;
import java.util.List;

public class SingleOnBulkRegistration<T> extends AbstractRegistration<Collection<T>, T> implements Registration {

	public SingleOnBulkRegistration(final List<T> initial, final T object) {
		super(initial, object, new DefaultRegistrationHandler<T>());
	}

	public static class DefaultRegistrationHandler<T> implements RegistrationHandler<Collection<T>, T> {

		@Override
		public void register(Collection<T> master, T object) {
			master.add(object);
		}

		@Override
		public void unregister(Collection<T> master, T object) {
			master.remove(object);
		}

	}
}
