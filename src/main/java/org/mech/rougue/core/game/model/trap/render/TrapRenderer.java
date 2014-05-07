package org.mech.rougue.core.game.model.trap.render;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.map.render.AbstractMapObjectRenderer;
import org.mech.rougue.core.game.model.trap.TileTrap;
import org.mech.rougue.core.game.model.trap.Trap;
import org.mech.rougue.core.game.play.component.map.GameMapTerminal;

public class TrapRenderer extends AbstractMapObjectRenderer<Trap> {

	@Override
	public void doRender(Trap trap, GameContext context, GameMapTerminal mapTerminal) {
		boolean needToShow = trap.isTrapActivated();
		if(needToShow){
			renderObject(trap, context, mapTerminal);
		}
	}

	@Override
	public String getType() {
		return TileTrap.TYPE;
	}

}
