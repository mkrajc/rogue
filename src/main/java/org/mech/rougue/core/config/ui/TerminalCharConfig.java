package org.mech.rougue.core.config.ui;

public class TerminalCharConfig {
	private CharConfig charConfig;
	private ColorConfig fgConfig;
	private ColorConfig bgConfig;
	
	private boolean bold = false;

	public CharConfig getCharConfig() {
		return charConfig;
	}

	public void setCharConfig(CharConfig charConfig) {
		this.charConfig = charConfig;
	}

	public ColorConfig getFgConfig() {
		return fgConfig;
	}

	public void setFgConfig(ColorConfig fgConfig) {
		this.fgConfig = fgConfig;
	}

	public ColorConfig getBgConfig() {
		return bgConfig;
	}

	public void setBgConfig(ColorConfig bgConfig) {
		this.bgConfig = bgConfig;
	}

	public boolean isBold() {
		return bold;
	}

	public void setBold(boolean bold) {
		this.bold = bold;
	}

}
