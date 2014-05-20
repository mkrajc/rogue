package org.mech.rougue.core.game.play.component.map;

import static org.junit.Assert.*;
import org.junit.Test;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.Position;

public class MapTerminalAdapterTest {

	@Test
	public void testGetTerminalPosition() {
		final MapTerminalAdapter adapter = new MapTerminalAdapter();
		
		assertEquals(Position.at(0, 0), adapter.getTerminalPosition(Dimension.of(10), Dimension.of(5), Position.at(0, 0)));
		assertEquals(Position.at(5, 5), adapter.getTerminalPosition(Dimension.of(10), Dimension.of(5), Position.at(9, 9)));
		assertEquals(Position.at(5, 0), adapter.getTerminalPosition(Dimension.of(10), Dimension.of(5), Position.at(9, 0)));
		assertEquals(Position.at(0, 5), adapter.getTerminalPosition(Dimension.of(10), Dimension.of(5), Position.at(0, 9)));
		assertEquals(Position.at(3, 3), adapter.getTerminalPosition(Dimension.of(10), Dimension.of(5), Position.at(5, 5)));
	}

}
