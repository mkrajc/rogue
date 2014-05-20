package org.mech.rougue.core.game.update.move;

import org.mech.rougue.core.game.model.map.tile.NewMapTile;
import org.mech.rougue.core.r.model.geom.Move;
import org.mech.rougue.core.r.model.geom.Positionable;
import org.mech.rougue.core.r.model.map.Map;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapMover {

	private static final Logger LOG = LoggerFactory.getLogger(MapMover.class);

	/**
	 * Apply Move on positionable object on map
	 * @param moveable
	 * @param move
	 * @param map
	 * @return true if object changed position
	 */
	public boolean move(Positionable moveable, Move move, Map map) {
		final Position position = moveable.getPosition();
		final Position shiftPosition = shiftPosition(position, move);
		return move(moveable, shiftPosition, map);
	}

	/**
	 * Move positionable to position object on map
	 * @param moveable
	 * @param move
	 * @param map
	 * @return true if object changed position, false if cannot move to that position
	 */
	public boolean move(Positionable moveable, Position destination, Map map) {
		// if moveable already on destination
		if (destination.equals(moveable.getPosition())) {
			return false;
		}

		final NewMapTile mapTile = map.get(destination);
		if (mapTile != null && mapTile.isFreeForMove()) {
			final NewMapTile old = map.get(moveable.getPosition());
			old.setOccupied(false);
			moveable.setPosition(destination);
			mapTile.setOccupied(true);
			return true;
		} else {
			LOG.trace("Cannot move [" + moveable + "] to position [" + destination + "]");
			return false;
		}
	}

	public final Position shiftPosition(Position p, Move move) {
		return shiftPosition(p, move, 1);
	}

	public final Position shiftPosition(Position p, Move move, int n) {
		return shiftPosition(p, move, n, null);
	}

	public final Position shiftPosition(Position p, Move move, int n, Dimension dimension) {
		int x = p.x + (move.x() * n);
		int y = p.y + (move.y() * n);

		// if (x < 0 || y < 0) {
		// throw new IllegalArgumentException("cannot shift to negative index");
		// }
		//
		// if (dimension != null && (x > dimension.width || y >
		// dimension.height)) {
		// throw new IllegalArgumentException("cannot shift out of dimension");
		// }

		return new Position(x, y);
	}

}
