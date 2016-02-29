package org.mech.rougue.core.game.model.map;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.mech.rogue.game.model.map.Map;
import org.mech.terminator.geometry.Position;

public class MapStats implements Serializable{
	private static final long serialVersionUID = 1555882417119209425L;
	//TODO redo to TIle itself
	private Set<Position> seenTiles = new HashSet<Position>();
	private Map map;

	public MapStats(final Map map) {
		this.map = map;
	}

	public boolean seen(final Position position) {
		return seenTiles.contains(position);
	}

	public void see(final Position position) {
		if (map.size().isIn(position)) {
			seenTiles.add(position);
		}
	}

	public int getSeenSize() {
		return seenTiles.size();
	}

	public void reset() {
		seenTiles.clear();
	}

	public void seeAll(final Collection<Position> positions) {
		for (final Position position : positions) {
			see(position);
		}
	}

}
