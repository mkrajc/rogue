package org.mech.rougue.core.game.model.map;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.mech.terminator.geometry.Position;

public class MapStats {
	//TODO redo to TIle itself
	private Set<Position> seenTiles = new HashSet<Position>();
	private Map map;

	public MapStats(Map map) {
		this.map = map;
	}

	public boolean seen(Position position) {
		return seenTiles.contains(position);
	}

	public void see(Position position) {
		if (map.isPositionInMap(position)) {
			seenTiles.add(position);
		}
	}

	public int getSeenSize() {
		return seenTiles.size();
	}

	public void reset() {
		seenTiles.clear();
	}

	public void seeAll(Collection<Position> positions) {
		for (Position position : positions) {
			see(position);
		}
	}

}
