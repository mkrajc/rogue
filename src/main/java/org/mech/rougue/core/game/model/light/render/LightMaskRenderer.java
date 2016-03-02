package org.mech.rougue.core.game.model.light.render;

import java.awt.Color;

import org.mech.rogue.game.render.map.Renderer;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.play.component.map.MapTerminalAdapter;
import org.mech.rougue.core.game.play.component.map.RenderedMapTile;
import org.mech.rougue.core.r.handler.game.light.LightMask;
import org.mech.rougue.utils.ColorUtils;
import org.mech.terminator.geometry.Position;

public class LightMaskRenderer implements Renderer {

    private static final Color SHADOW = Color.BLACK;
    private static final Color LIGHT = Color.WHITE;

    public void render(final Position at, final GameContext context, final MapTerminalAdapter mapTerminal) {
        final LightMask lightMask = context.getLightMask();
        final boolean lighten = lightMask.isLighten(at);
        final RenderedMapTile rTile = mapTerminal.get(at);

        if (rTile == null) {
            //propably terminal has changed in meantime and is not necessary to continue in rendering
            return;
        }

        final Color bgColor = rTile.getBg();

        Color newColor = bgColor == null ? Color.BLACK : ColorUtils.blend(bgColor, SHADOW, lightMask.getShadowIntensity(at));
        if (lighten) {
            newColor = ColorUtils.blend(LIGHT, newColor, lightMask.getLightIntensity(at));
        }

        rTile.setGrayscale(!lighten);
        rTile.setBg(newColor);
    }

}

