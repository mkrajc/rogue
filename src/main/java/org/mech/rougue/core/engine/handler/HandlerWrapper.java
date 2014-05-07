package org.mech.rougue.core.engine.handler;

import org.mech.rougue.core.engine.Engine;
import org.mech.rougue.core.engine.handler.input.InputHandler;
import org.mech.rougue.core.engine.handler.render.RenderHandler;
import org.mech.rougue.core.engine.handler.update.UpdateHandler;
import org.mech.rougue.factory.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class HandlerWrapper implements Handler {

	private final static Logger LOG = LoggerFactory.getLogger(HandlerWrapper.class);

	@Inject
	private Engine engine;

	protected InputHandler getInputHandler() {
		return null;
	}

	protected RenderHandler getRenderHandler() {
		return null;
	}

	protected UpdateHandler getUpdateHandler() {
		return null;
	}

	@Override
	public void processInput() {
		if (getInputHandler() != null) {
			getInputHandler().processInput();
		} else {
			LOG.warn(getClass() + " input handler not configured");
		}

	}

	@Override
	public void update() {
		if (getUpdateHandler() != null) {
			getUpdateHandler().update();
		} else {
			LOG.warn(getClass() + " update handler not configured");
		}
	}

	@Override
	public void render() {
		if (getRenderHandler() != null) {
			getRenderHandler().render();
		} else {
			LOG.warn(getClass() + " render handler not configured");
		}
	}

	public void _switch_() {
		engine.setHandler(this);
	}

}
