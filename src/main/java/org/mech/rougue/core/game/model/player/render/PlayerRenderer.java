package org.mech.rougue.core.game.model.player.render;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.map.render.AbstractMapObjectRenderer;
import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.game.play.component.map.MapTerminalAdapter;
import org.mech.rougue.core.game.play.component.map.RenderedMapTile;
import org.mech.rougue.core.game.play.component.map.cursor.Cursor;

public class PlayerRenderer extends AbstractMapObjectRenderer<Player> {

		private Cursor playerCursor;

	@Override
	public void doRender(Player player, GameContext context, MapTerminalAdapter mapTerminal) {
		renderObject(player, context, mapTerminal);
		updateCursor(player, context, mapTerminal);
	}

	private void updateCursor(Player player, GameContext context, MapTerminalAdapter mapTerminal) {
		if (context.state.isTurnFrozen()) {
			if (playerCursor == null) {
				playerCursor = new PlayerCursor();
			}
			final RenderedMapTile mapTile = mapTerminal.get(player.getPosition());
			playerCursor.update(mapTile);
		} else {
			playerCursor = null;
		}
	}

	@Override
	public String getType() {
		return "player";
	}

}
