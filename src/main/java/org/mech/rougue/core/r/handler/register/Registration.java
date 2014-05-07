package org.mech.rougue.core.r.handler.register;

public interface Registration {
	void register();
	void unregister();
	void destroy();
	boolean isRegistered();
}
