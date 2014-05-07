package org.mech.rougue.core.game.model.map.render;

import org.mech.rougue.core.config.ui.ColorConfigUtils;
import org.mech.rougue.core.config.ui.TerminalCharConfig;
import org.mech.rougue.core.config.ui.provider.SimpleTerminalConfigProvider;
import org.mech.rougue.core.game.model.map.decorator.Decorator;
import org.mech.rougue.core.game.model.map.decorator.Decorator.IdDecorator;
import org.mech.rougue.core.game.model.map.tile.TileTheme;
import org.mech.rougue.core.game.play.component.map.GameMapTerminal;
import org.mech.rougue.core.game.play.component.map.RenderedMapTile;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.geometry.Position;

public abstract class AbstractMapRenderer implements MapRenderer {

	@Inject
	private SimpleTerminalConfigProvider configProvider;

	@Inject
	private TileTheme theme;
	
	@Inject 
	private Decorator decorator;

	protected void render(GameMapTerminal mapTerminal, RenderedMapTile rTile, RenderId id, Position p) {
		check(id, p);
		final TerminalCharConfig config = configProvider.provide(id.getFinalId());
		rTile.setChar(config.getCharConfig().get());
		rTile.setFg(ColorConfigUtils.getFixedColor(config.getFgConfig()));
		rTile.setBg(ColorConfigUtils.getFixedColor(config.getBgConfig()));
		rTile.setBold(config.isBold());
	}

	protected void check(RenderId id, Position p) {
		if (id.getFinalId() == null) {
			final IdDecorator idDecorator = decorator.getIdDecorator();
			idDecorator.decorate(id, p);
			theme.updateRenderId(id);
		} 
	}

}
