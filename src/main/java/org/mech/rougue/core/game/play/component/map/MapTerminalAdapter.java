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

	private NestedRectangle terminalToMap;
	private Rectangle mapBoundary;

	private ITerminal terminal;

	private org.mech.rougue.core.r.model.map.Map gameMap;
	private Map<Position, RenderedMapTile> tiles;

	public MapTerminalAdapter() {}

	public Dimension getSize() {
		return terminalToMap.getSize();
	}

	public Position toTerminal(final Position mapPosition) {
		return terminalToMap.outerRelToRel(mapPosition);
	}

	protected Position getTerminalPosition(final Dimension mapSize, final Dimension terminalSize, final Position playerPosition) {
		final int coordX = getCoord(playerPosition.x, mapSize.width, terminalSize.width);
		final int coordY = getCoord(playerPosition.y, mapSize.height, terminalSize.height);
		return Position.at(coordX, coordY);
	}

	protected Position getMapPosition(final Dimension mapSize, final Dimension terminalSize, final Position playerPosition) {
		final int coordX = getCoord(playerPosition.x, mapSize.width, terminalSize.width);
		final int coordY = getCoord(playerPosition.y, mapSize.height, terminalSize.height);
		return Position.at(coordX, coordY);
	}

	private int getCoord(final int player, final int map, final int term) {
		if (map < term) {
			return (map - term) / 2;
		} else {
			int max = Math.max(player - term / 2, 0);
			max = Math.min(max, map - term);
			return max;
		}
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
		return mapBoundary;
	}

	public void adapt(final GameContext context) {
		this.tiles = new HashMap<Position, RenderedMapTile>();

		final GameData game = context.getData();
		this.gameMap = game.getMap();
		this.terminal = Terminal.getInstance();

		final Dimension mapSize = gameMap.getSize();
		final Dimension termSize = terminal.getSize().toDimension();

		final Position playerPosition = game.getPlayer().getPosition();
		final Position terminalMapShiftPosition = getTerminalPosition(mapSize, termSize, playerPosition);

		// todo map boundary could be different
		terminalToMap = new NestedRectangle(terminalMapShiftPosition, termSize, gameMap.getSize().toRectangle());
		mapBoundary = terminalToMap.intersect();
	}
}
