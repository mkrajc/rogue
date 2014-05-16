package org.mech.rougue.core.engine.handler.input;

import java.awt.event.KeyEvent;

public class Inputs {

	public static boolean isArrowKey(KeyEvent event) {
		int keyCode = event.getKeyCode();
		return (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN);
	}

	public static boolean isLeftArrowKey(KeyEvent event) {
		return (event.getKeyCode() == KeyEvent.VK_LEFT);
	}

	public static boolean isRightArrowKey(KeyEvent event) {
		return (event.getKeyCode() == KeyEvent.VK_RIGHT);
	}

	public static boolean isUpArrowKey(KeyEvent event) {
		return (event.getKeyCode() == KeyEvent.VK_UP);
	}

	public static boolean isDownArrowKey(KeyEvent event) {
		return (event.getKeyCode() == KeyEvent.VK_DOWN);
	}

	public static boolean isEnter(KeyEvent event) {
		return (event.getKeyCode() == KeyEvent.VK_ENTER);
	}
}
