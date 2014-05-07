package org.mech.rougue.core.r.handler.register;

public interface RegistrationHandler<M, O> {
	/** 
	 * Register object on master object
	 * @param item
	 * @param on
	 */
	void register(M master, O object);

	/** 
	 * Unregister object from master object
	 * @param item
	 * @param on
	 */
	void unregister(M master, O object);
}
