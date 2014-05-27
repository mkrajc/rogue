package org.mech.rougue.core.game.model.player.render;

import org.mech.rougue.core.game.play.component.map.RenderedMapTile;
import org.mech.rougue.core.game.play.component.map.cursor.MapCursor;
import org.mech.rougue.utils.ColorUtils;

public class PlayerCursor extends MapCursor {

	@Override
	public int getSpeed() {
		return 30;
	}

	@Override
	protected void updateCursor(RenderedMapTile mapTile) {
		mapTile.setBg(ColorUtils.inverse(mapTile.getBg()));
		// mapTile.setFg(ColorUtils.inverse(mapTile.getFg()));
	}

}
