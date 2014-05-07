package org.mech.rougue.core.game.play.component.map;

import java.awt.Color;
import org.mech.rougue.core.config.ui.ColorConfigUtils;
import org.mech.rougue.core.game.model.map.tile.MapTile;
import org.mech.rougue.core.game.model.map.tile.NewMapTile;
import org.mech.terminator.ITerminal;
import org.mech.terminator.geometry.Position;

public class RenderedMapTile {

	private ITerminal terminal;

	private boolean grayscale = false;
	private Color fg;
	private Color bg;
	private char c;
	private boolean bold = false;
	
	private Position terminalPosition;
	private NewMapTile tile;

	public RenderedMapTile(Position termPosition, ITerminal terminal, NewMapTile tile) {
		if (termPosition == null) {
			System.out.println("help");
		}
		this.terminalPosition = termPosition;
		this.terminal = terminal;
		this.tile = tile;
	}

	public void setGrayscale(boolean value) {
		grayscale = value;
	}

	public boolean isGrayscale() {
		return grayscale;
	}

	public char getChar() {
		return c;
	}

	public void setChar(char c) {
		this.c = c;
	}

	public void render() {
		terminal.put(getChar(), getLine(), getColumn());
		Color bgColor = bg;
		Color fgColor = fg;

		if (isGrayscale()) {
			fgColor = ColorConfigUtils.toGrayScale(fg);
		}

		if (bold) {
			terminal.bold(getLine(), getColumn());
		}
		
		terminal.bg(bgColor, getLine(), getColumn());
		terminal.fg(fgColor, getLine(), getColumn());
	}

	public void setBg(Color color) {
		if (color != null) {
			this.bg = color;
		}
	}

	public void setFg(Color color) {
		if (color != null) {
			this.fg = color;
		}
	}

	public Color getFg() {
		return fg;
	}

	public Color getBg() {
		return bg;
	}

	public int getLine() {
		return terminalPosition.y;
	}

	public int getColumn() {
		return terminalPosition.x;
	}

	public NewMapTile getTile() {
		return tile;
	}

	public boolean isBold() {
		return bold;
	}

	public void setBold(boolean bold) {
		this.bold = bold;
	}

}
