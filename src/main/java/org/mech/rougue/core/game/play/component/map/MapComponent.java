package org.mech.rougue.core.game.play.component.map;

import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.map.render.AbstractOrderedMapRenderer;
import org.mech.rougue.core.game.play.handler.GameInput;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.ITerminal;
import org.mech.terminator.component.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapComponent extends Component {

	private static final Logger LOG = LoggerFactory.getLogger(MapComponent.class);

	@Inject
	private GameContext context;

	@Inject
	private GameInput gameInput;

	@Inject
	private List<AbstractOrderedMapRenderer> renderers;

	@PostConstruct
	public void setup() {
		addInputListener(gameInput);
	}

	@Override
	public void onRender(ITerminal terminal) {

		final GameMapTerminal mapTerminal = new GameMapTerminal(context, terminal);

		for (AbstractOrderedMapRenderer gameRenderer : renderers) {
			LOG.trace(gameRenderer.getClass().getSimpleName() + " invoking ...");
			gameRenderer.render(context, mapTerminal);
		}

		final Collection<RenderedMapTile> tiles = mapTerminal.getTiles();

		for (RenderedMapTile tile : tiles) {
			tile.render();
		}

	}

	//	private void renderTile(ITerminal terminal, RenderedMapTile tile) {
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
