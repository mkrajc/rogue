package org.mech.rougue.core.r.handler.register;

public class NullRegistration implements Registration {

	@Override
	public void register() {}

	@Override
	public void unregister() {}

	@Override
	public void destroy() {}

	@Override
	public boolean isRegistered() {
		return false;
	}

}
