package org.mech.rougue.core.game.play.component;

import org.mech.rougue.core.game.play.handler.GameUpdate;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.ITerminal;
import org.mech.terminator.component.Label;
import org.mech.terminator.geometry.Dimension;

public class MapLabel extends Label {

	@Inject
	private GameUpdate gameUpdate;

	private static final String LABEL = "@w%s";

	public MapLabel() {
		super("");
		setPrefferedSize(Dimension.of(0, 2));
	}

	@Override
	public void onRender(ITerminal terminal) {
		setText(String.format(LABEL, gameUpdate.getGame().getPlayer().getPosition()));
		super.onRender(terminal);
	}

}
