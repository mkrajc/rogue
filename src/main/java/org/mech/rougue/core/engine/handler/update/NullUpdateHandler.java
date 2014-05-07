package org.mech.rougue.core.engine.handler.update;

public class NullUpdateHandler implements UpdateHandler {

	public static final UpdateHandler INSTANCE = new NullUpdateHandler();

	@Override
	public void update() {

	}

}
