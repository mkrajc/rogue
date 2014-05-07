package org.mech.rougue.core.game.play.component.map.cursor;

import org.mech.rougue.core.game.play.component.map.RenderedMapTile;

public interface Cursor {

	int getSpeed();

	void update(RenderedMapTile mapTile);

}
