package org.mech.rougue.core.game.play.component.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.GameData;
import org.mech.rougue.core.game.model.map.tile.NewMapTile;
import org.mech.terminator.ITerminal;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.NestedRectangle;
import org.mech.terminator.geometry.Position;

public class GameMapTerminal {
	private GameMapScaler gameMapScaler = new GameMapScaler();
	private NestedRectangle area;
	private ITerminal terminal;

	private org.mech.rougue.core.game.model.map.Map gameMap;
	private Map<Position, RenderedMapTile> tiles;

	public GameMapTerminal(GameContext context, ITerminal trmnl) {
		this.tiles = new HashMap<Position, RenderedMapTile>();

		final GameData game = context.getData();
		this.gameMap = game.getMap();
		this.terminal = gameMapScaler.upscaleIfNeeded(gameMap, trmnl);

		final Dimension mapSize = gameMap.getSize();
		final Dimension termSize = terminal.getSize().toDimension();

		final Position playerPosition = game.getPlayer().getPosition();
		final Position mapPosition = getMapPosition(mapSize, termSize, playerPosition);

		area = new NestedRectangle(mapPosition, termSize, mapSize);
	}

	public Dimension getSize() {
		return area.getSize();
	}

	public Position toTerminal(Position mapPosition) {
		return area.outerToInner(mapPosition);
	}

	// private Position toMap(Position termPosition) {
	// return area.innerToOuter(termPosition);
	// }

	private Position getMapPosition(Dimension mapSize, Dimension visibleArea, Position playerPosition) {
		int x = Math.max(playerPosition.x - visibleArea.width / 2, 0);
		int y = Math.max(playerPosition.y - visibleArea.height / 2, 0);

		x = Math.min(x, mapSize.width - visibleArea.width);
		y = Math.min(y, mapSize.height - visibleArea.height);
		return Position.at(x, y);
	}

	public RenderedMapTile get(Position mapPosition) {
		if (mapPosition == null) {
			return null;
		}

		final Position termPosition = toTerminal(mapPosition);
		if (termPosition == null) {
			return null;
		}

		RenderedMapTile mapTile = tiles.get(termPosition);

		if (mapTile == null) {
			NewMapTile t = gameMap.get(mapPosition);
			mapTile = new RenderedMapTile(termPosition, terminal, t);
			tiles.put(termPosition, mapTile);
		}
		return mapTile;
	}

	public Collection<RenderedMapTile> getTiles() {
		return tiles.values();
	}

	public RenderedMapTile get(int x, int y) {
		return get(Position.at(x, y));
	}

	public NestedRectangle getBoundary() {
		return area;
	}
}
