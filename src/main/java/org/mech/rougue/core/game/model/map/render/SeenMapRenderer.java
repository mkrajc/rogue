package org.mech.rougue.core.game.model.map.render;

import org.mech.rogue.game.render.map.Renderer;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.map.tile.TileConstants;
import org.mech.rougue.core.game.play.component.map.MapTerminalAdapter;
import org.mech.rougue.core.game.play.component.map.RenderedMapTile;
import org.mech.rougue.core.r.render.RenderId;
import org.mech.rougue.core.r.render.terminal.DefaultTerminalConfigProvider;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.geometry.Position;

public class SeenMapRenderer implements Renderer{

    @Inject
    private DefaultTerminalConfigProvider configProvider;

    public static final String SEE_ALL_SWITCH = "game.map.see.all";

    public void render(final Position at, final GameContext context, final MapTerminalAdapter mapTerminal) {

        final RenderedMapTile rTile = mapTerminal.get(at);

        if (rTile == null) {
            //propably terminal has changed in meantime and is not necessary to continue in rendering
            return;
        }

        final boolean seen = seePosition(context, at);
        if (seen) {
            RTileRenderer.render(configProvider, rTile, rTile.getTile().renderId(), at);
        } else {
            RTileRenderer.render(configProvider, rTile, getCoveredId(at), at);
        }


    }


    private boolean seePosition(final GameContext context, final Position position) {
        return context.getState().getSwitch(SEE_ALL_SWITCH) || context.getData().getMap().stats().seen(position);
    }

    private RenderId getCoveredId(final Position p) {
        if (p.x % 2 == 0 && p.y % 2 == 0 && (p.x + p.y) % 4 == 2) {
            return new RenderId(TileConstants.COVERED);
        } else if (p.x % 2 == 1 && p.y % 2 == 1) {
            return new RenderId(TileConstants.COVERED_DOT);
        }
        return new RenderId(TileConstants.COVERED_EMPTY);
    }
}
