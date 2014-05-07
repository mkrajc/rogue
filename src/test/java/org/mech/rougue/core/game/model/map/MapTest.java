package org.mech.rougue.core.game.model.map;

import static org.junit.Assert.*;
import org.junit.Test;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.Position;

public class MapTest {

	@Test
	public void testGet() {
		Map map = new Map(Dimension.of(4, 4));
		assertNull(map.get(Position.at(-1, 0)));
		assertNull(map.get(Position.at(0, -1)));
		assertNull(map.get(Position.at(0, 4)));
		assertNull(map.get(Position.at(4, 0)));
		assertNull(map.get(Position.at(4, 4)));
		assertNotNull(map.get(Position.at(0, 0)));
		assertNotNull(map.get(Position.at(3, 3)));
	}

	@Test
	public void testGetCoord() {
		Map map = new Map(Dimension.of(4, 4));
		assertNull(map.get(-1, 0));
		assertNull(map.get(0, -1));
		assertNull(map.get(0, 4));
		assertNull(map.get(4, 0));
		assertNull(map.get(4, 4));
		assertNotNull(map.get(0, 0));
		assertNotNull(map.get(3, 3));
	}

}
