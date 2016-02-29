package org.mech.rougue.core.game.model.map.render;

import org.mech.rogue.game.model.map.Ground;
import org.mech.rogue.game.model.map.Ground$;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.map.tile.TileConstants;
import org.mech.rougue.core.game.play.component.map.MapTerminalAdapter;
import org.mech.rougue.core.game.play.component.map.RenderedMapTile;
import org.mech.rougue.core.game.state.GameState;
import org.mech.rougue.core.r.render.RenderId;
import org.mech.rougue.core.r.render.terminal.DefaultTerminalConfigProvider;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.geometry.Position;
import org.mech.terminator.geometry.Rectangle;

public class SeenMapRenderer extends AbstractOrderedMapRenderer {

	public static final String SEE_ALL_SWITCH = "game.map.see.all";

	@Inject
	private DefaultTerminalConfigProvider configProvider;

	@Inject
	private GameState state;

	@Override
	public void render(final GameContext context, final MapTerminalAdapter mapTerminal) {
		final Rectangle rectangle = mapTerminal.getBoundary();
		final Position start = rectangle.getTopLeftPosition();
		final Position end = rectangle.getBottomRightPosition();

		for (int i = start.x; i <= end.x; i++) {
			for (int j = start.y; j <= end.y; j++) {
				final Position at = Position.at(i, j);
				final RenderedMapTile rTile = mapTerminal.get(at);

				if (rTile == null) {
					//propably terminal has changed in meantime and is not necessary to continue in rendering
					break;
				}

				final boolean seen = seePosition(context, at);

				if (seen) {
					render(mapTerminal, rTile, rTile.getTile().renderId(), at);
				} else {
					render(mapTerminal, rTile, getCoveredId(at), at);
				}
			}
		}

	}

	private boolean seePosition(final GameContext context, final Position position) {
		return state.getSwitch(SEE_ALL_SWITCH) || context.getData().getMap().getStats().seen(position);
	}

	private RenderId getCoveredId(final Position p) {
		if (p.x % 2 == 0 && p.y % 2 == 0 && (p.x + p.y) % 4 == 2) {
			return new RenderId(TileConstants.COVERED);
		} else if (p.x % 2 == 1 && p.y % 2 == 1) {
			return new RenderId(TileConstants.COVERED_DOT);
		}
		return new RenderId(TileConstants.COVERED_EMPTY);
	}

	@Override
	public int getOrder() {
		return RenderOrder.MAP.ordinal();
	}

}
