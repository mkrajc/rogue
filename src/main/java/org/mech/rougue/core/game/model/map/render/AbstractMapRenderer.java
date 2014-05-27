package org.mech.rougue.core.game.model.map.render;

import org.mech.rougue.core.game.play.component.map.MapTerminalAdapter;
import org.mech.rougue.core.game.play.component.map.RenderedMapTile;
import org.mech.rougue.core.r.render.RenderId;
import org.mech.rougue.core.r.render.terminal.ColorConfigUtils;
import org.mech.rougue.core.r.render.terminal.DefaultTerminalConfigProvider;
import org.mech.rougue.core.r.render.terminal.TerminalCharConfig;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.geometry.Position;

public abstract class AbstractMapRenderer implements MapRenderer {

	@Inject
	private DefaultTerminalConfigProvider configProvider;

	protected void render(final MapTerminalAdapter mapTerminal, final RenderedMapTile rTile, final RenderId id, final Position p) {
		final RenderId finalId = check(id, p);
		if (finalId != null && finalId.getGeneratedId() != null) {
			final TerminalCharConfig config = configProvider.provide(finalId.getGeneratedId());
			rTile.setChar(config.getCharConfig().get());
			rTile.setFg(ColorConfigUtils.getFixedColor(config.getFgConfig()));
			rTile.setBg(ColorConfigUtils.getFixedColor(config.getBgConfig()));
			rTile.setBold(config.isBold());
		}
	}

	protected RenderId check(final RenderId id, final Position p) {
		if (id != null) {
			configProvider.regenerate(id, p);
		}

		return id;
	}

}
