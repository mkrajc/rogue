package org.mech.rougue.core.game.model.light.render;

import java.awt.Color;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.map.render.AbstractOrderedMapRenderer;
import org.mech.rougue.core.game.model.map.render.RenderOrder;
import org.mech.rougue.core.game.play.component.map.GameMapTerminal;
import org.mech.rougue.core.game.play.component.map.RenderedMapTile;
import org.mech.rougue.core.r.handler.game.light.LightMask;
import org.mech.rougue.ui.color.ColorUtils;
import org.mech.terminator.geometry.NestedRectangle;
import org.mech.terminator.geometry.Position;

public class LightMaskRenderer extends AbstractOrderedMapRenderer {

	private static final Color SHADOW = Color.BLACK;
	private static final Color LIGHT = Color.WHITE;

	@Override
	public void render(GameContext context, GameMapTerminal mapTerminal) {

		final NestedRectangle rectangle = mapTerminal.getBoundary();
		final Position start = rectangle.getTopLeftPosition();
		final Position end = rectangle.getBottomRightPosition();

		final LightMask lightMask = context.getGameObject(LightMask.class);

		for (int i = start.x; i <= end.x; i++) {
			for (int j = start.y; j <= end.y; j++) {
				final Position at = Position.at(i, j);
				final boolean lighten = lightMask.isLighten(at);

				final RenderedMapTile rTile = mapTerminal.get(at);
				final Color bgColor = rTile.getBg();

				Color newColor = bgColor == null ? Color.BLACK : ColorUtils.blend(bgColor, SHADOW, lightMask.getShadowIntensity(at));
				if (lighten) {
					newColor = ColorUtils.blend(LIGHT, newColor, lightMask.getLightIntensity(at));
				}

				rTile.setGrayscale(!lighten);
				rTile.setBg(newColor);

			}
		}
	}

	@Override
	public int getOrder() {
		return RenderOrder.LIGHTMASK.ordinal();
	}
}
