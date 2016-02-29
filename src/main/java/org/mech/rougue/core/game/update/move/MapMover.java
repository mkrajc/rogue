package org.mech.rougue.core.game.update.move;

import org.mech.rogue.game.model.map.Ground$;
import org.mech.rogue.game.model.map.Map;
import org.mech.rogue.game.model.map.MapTile;
import org.mech.rougue.core.r.model.geom.Move;
import org.mech.rougue.core.r.model.geom.Positionable;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import scala.Option;

public class MapMover {

	private static final Logger LOG = LoggerFactory.getLogger(MapMover.class);

	/**
	 * Apply Move on positionable object on map
	 * @param moveable
	 * @param move
	 * @param map
	 * @return true if object changed position
	 */
	public boolean move(final Positionable moveable, final Move move, final Map map) {
		final Position position = moveable.getPosition();
		final Position shiftPosition = shiftPosition(position, move);
		return move(moveable, shiftPosition, map);
	}

	/**
	 * Move positionable to position object on map
	 * @param moveable
	 * @param map
	 * @return true if object changed position, false if cannot move to that position
	 */
	public boolean move(final Positionable moveable, final Position destination, final Map map) {
		// if moveable already on destination
		if (destination.equals(moveable.getPosition())) {
			return false;
		}

		final MapTile mapTile = map.get(destination).getOrElse(null);
		if (mapTile != null && Ground$.MODULE$.equals(mapTile.config().tileType())){
			moveable.setPosition(destination);
			return true;
		} else {
			LOG.trace("Cannot move [" + moveable + "] to position [" + destination + "]");
			return false;
		}
	}

	public void displace(final Positionable moveable, final Position destination, final Map map) {
		moveable.setPosition(null);
	}

	public void place(final Positionable moveable, final Position destination, final Map map) {
		final MapTile mapTile = map.get(destination).getOrElse(null);

		if (mapTile != null && Ground$.MODULE$.equals(mapTile.config().tileType())) {
			moveable.setPosition(destination);
		} else {
			throw new IllegalArgumentException("Cannot place on position [obj=" + moveable + ", position" + destination + "]");
		}
	}

	public final Position shiftPosition(final Position p, final Move move) {
		return shiftPosition(p, move, 1);
	}

	public final Position shiftPosition(final Position p, final Move move, final int n) {
		return shiftPosition(p, move, n, null);
	}

	public final Position shiftPosition(final Position p, final Move move, final int n, final Dimension dimension) {
		final int x = p.x + (move.x() * n);
		final int y = p.y + (move.y() * n);

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
