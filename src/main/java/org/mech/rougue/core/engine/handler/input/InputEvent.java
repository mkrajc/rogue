package org.mech.rougue.core.engine.handler.input;

public class InputEvent {
	private final int code;

	public InputEvent(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public char getChar() {
		return (char) code;
	}

	@Override
	public String toString() {
		return String.valueOf(code);
	}

}
