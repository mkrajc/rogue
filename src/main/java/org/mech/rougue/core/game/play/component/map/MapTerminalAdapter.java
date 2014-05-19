package org.mech.rougue.core.game.play.component.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.GameData;
import org.mech.rougue.core.game.model.map.tile.NewMapTile;
import org.mech.terminator.ITerminal;
import org.mech.terminator.Terminal;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.NestedRectangle;
import org.mech.terminator.geometry.Position;
import org.mech.terminator.geometry.Rectangle;

public class MapTerminalAdapter {
//	private final GameMapScaler gameMapScaler = new GameMapScaler();
	private final NestedRectangle area;
	private final ITerminal terminal;

	private final org.mech.rougue.core.game.model.map.Map gameMap;
	private final Map<Position, RenderedMapTile> tiles;

	public MapTerminalAdapter(final GameContext context) {
		this.tiles = new HashMap<Position, RenderedMapTile>();

		final GameData game = context.getData();
		this.gameMap = game.getMap();
		this.terminal =  Terminal.getInstance(); //gameMapScaler.upscaleIfNeeded(gameMap, trmnl);

		final Dimension mapSize = gameMap.getSize();
		final Dimension termSize = terminal.getSize().toDimension();

		final Position playerPosition = game.getPlayer().getPosition();
		final Position mapPosition = getMapPosition(mapSize, termSize, playerPosition);
		final Rectangle termBoundary = new Rectangle(mapPosition, termSize);
		
		area = new NestedRectangle(mapPosition, mapSize, termBoundary);
	}

	public Dimension getSize() {
		return area.getSize();
	}

	public Position toTerminal(final Position mapPosition) {
		return area.innerAbsToRel(mapPosition);
	}

	private Position getMapPosition(final Dimension mapSize, final Dimension visibleArea, final Position playerPosition) {
		int x = Math.max(playerPosition.x - visibleArea.width / 2, 0);
		int y = Math.max(playerPosition.y - visibleArea.height / 2, 0);

		x = Math.min(x, mapSize.width - visibleArea.width);
		y = Math.min(y, mapSize.height - visibleArea.height);
		
		x = Math.max(x, 0);
		y = Math.max(y, 0);
		
		return Position.at(x, y);
	}

	public RenderedMapTile get(final Position mapPosition) {
		if (mapPosition == null) {
			return null;
		}

		final Position termPosition = toTerminal(mapPosition);
		if (termPosition == null) {
			return null;
		}

		RenderedMapTile mapTile = tiles.get(termPosition);

		if (mapTile == null) {
			final NewMapTile t = gameMap.get(mapPosition);
			mapTile = new RenderedMapTile(termPosition, terminal, t);
			tiles.put(termPosition, mapTile);
		}
		return mapTile;
	}

	public Collection<RenderedMapTile> getTiles() {
		return tiles.values();
	}

	public RenderedMapTile get(final int x, final int y) {
		return get(Position.at(x, y));
	}

	public Rectangle getBoundary() {
		return area.intersect();
	}
}
