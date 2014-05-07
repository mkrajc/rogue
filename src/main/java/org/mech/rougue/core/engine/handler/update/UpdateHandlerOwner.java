package org.mech.rougue.core.engine.handler.update;

public interface UpdateHandlerOwner {
	void setUpdateHandler(UpdateHandler handler);

	UpdateHandler getUpdateHandler();
}
