package org.mech.rougue.core.game.play.component.map;

import java.util.Collection;
import java.util.List;
import org.mech.rougue.core.engine.handler.render.RenderHandler;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.map.render.AbstractOrderedMapRenderer;
import org.mech.rougue.factory.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapComponent implements RenderHandler {

	private static final Logger LOG = LoggerFactory.getLogger(MapComponent.class);

	@Inject
	private GameContext context;

	//	@Inject
	//	private GameInput gameInput;

	@Inject
	private List<AbstractOrderedMapRenderer> renderers;

	//	@PostConstruct
	//	public void setup() {
	//		addInputListener(gameInput);
	//	}

	@Override
	public void render() {
		final GameMapTerminal mapTerminal = new GameMapTerminal(context);

		for (final AbstractOrderedMapRenderer gameRenderer : renderers) {
			LOG.trace(gameRenderer.getClass().getSimpleName() + " invoking ...");
			gameRenderer.render(context, mapTerminal);
		}
		final Collection<RenderedMapTile> tiles = mapTerminal.getTiles();

		for (final RenderedMapTile tile : tiles) {
			tile.render();
		}
	}

	//	private void renderTile(ITermirminal, RenderedMapTile tile) {
	//		int line = tile.getLine();
	//		int column = tile.getColumn();
	//		terminal.put(tile.getChar(), line, column);
	//		Color bgColor = tile.getBg();
	//		Color fgColor = tile.getFg();
	//
	//		if (tile.isGrayscale()) {
	//			fgColor = ColorConfigUtils.toGrayScale(tile.getFg());
	//		}
	//
	//		if(tile.isBold()) {
	//			terminal.bold(line, column);
	//		}
	//
	//		terminal.bg(bgColor, line, column);
	//		terminal.fg(fgColor, line, column);
	//	}

}
