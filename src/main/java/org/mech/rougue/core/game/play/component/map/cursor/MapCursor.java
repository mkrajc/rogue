package org.mech.rougue.core.game.play.component.map.cursor;

import org.mech.rougue.core.game.play.component.map.RenderedMapTile;
import org.mech.rougue.core.r.model.geom.Positionable;
import org.mech.terminator.geometry.Position;

public abstract class MapCursor implements Positionable, Cursor {
	private Position position;
	private int counter = 0;
	private boolean cursor = false;

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public void update(RenderedMapTile mapTile) {
		if (counter++ == getSpeed()) {
			cursor = !cursor;
			counter = 0;
		}

		if (cursor) {
			updateCursor(mapTile);
		}
	}

	protected abstract void updateCursor(RenderedMapTile mapTile);
}
