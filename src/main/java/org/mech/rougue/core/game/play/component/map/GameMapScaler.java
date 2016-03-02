package org.mech.rougue.core.game.play.component.map;


import org.mech.rogue.game.model.map.Map;
import org.mech.terminator.ITerminal;
import org.mech.terminator.TerminalRectangleWrapper;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.Position;
import org.mech.terminator.geometry.Rectangle;

public class GameMapScaler {

	private Dimension lastMapSize;
	private Dimension lastTerminalSize;

	private boolean rebuildNeeded = true;
	private ITerminal term;

	public GameMapScaler() {}

	public ITerminal upscaleIfNeeded(Map map, ITerminal iTerminal) {
		Dimension terminalDimension = iTerminal.getSize().toDimension();
		Dimension mapDimension = map.size();
		if (rebuildNeeded) {
			lastMapSize = Dimension.clone(mapDimension);
			lastTerminalSize = Dimension.clone(terminalDimension);
			recount(iTerminal, mapDimension, terminalDimension);
		}

		if (lastMapSize == null || !lastMapSize.equals(mapDimension) || lastTerminalSize == null || !lastTerminalSize.equals(terminalDimension)) {
			recount(iTerminal, mapDimension, terminalDimension);
		}

		return term;
	}

	private void recount(ITerminal iTerminal, Dimension mapDimension, Dimension terminalDimension) {
		boolean upscale = mapDimension.height < terminalDimension.height || mapDimension.width < terminalDimension.width;
		rebuildNeeded = false;

		if (upscale) {
			int h = Math.max((terminalDimension.height - mapDimension.height) / 2, 0);
			int w = Math.max((terminalDimension.width - mapDimension.width) / 2, 0);

			int limitW = Math.min(terminalDimension.width, mapDimension.width);
			int limitH = Math.min(terminalDimension.height, mapDimension.height);

			Rectangle visible = new Rectangle(Position.at(w, h), Dimension.of(limitW, limitH));

			term = new TerminalRectangleWrapper(visible, iTerminal);
		} else {
			term = iTerminal;
		}
	}
}
