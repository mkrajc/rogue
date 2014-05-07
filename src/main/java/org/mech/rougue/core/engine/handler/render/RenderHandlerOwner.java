package org.mech.rougue.core.engine.handler.render;

public interface RenderHandlerOwner {
	void setRenderHandler(RenderHandler handler);

	RenderHandler getRenderHandler();
}
