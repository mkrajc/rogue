package org.mech.rougue.core.engine.handler;

import org.mech.rougue.core.engine.handler.input.InputHandler;
import org.mech.rougue.core.engine.handler.render.RenderHandler;
import org.mech.rougue.core.engine.handler.update.UpdateHandler;

public interface Handler extends InputHandler, UpdateHandler, RenderHandler {}
