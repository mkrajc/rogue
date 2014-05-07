package org.mech.rougue.core.r.handler.register;

import java.util.Collection;

public class BulkOnBulkRegistration<T> extends BulkRegistration<Collection<T>, T> {

	public BulkOnBulkRegistration(final Collection<T> master, final Collection<T> partial) {
		super(master, partial, new SingleOnBulkRegistration.DefaultRegistrationHandler<T>());
	}

	public void addToPartial(final T o) {
		this.object.add(o);
	}

}
