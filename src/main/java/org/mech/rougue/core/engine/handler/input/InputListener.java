package org.mech.rougue.core.engine.handler.input;

import java.awt.event.KeyEvent;

import org.mech.rougue.core.game.GameContext;

public interface InputListener {
	void handleInput(KeyEvent event);
}
