package org.mech.rougue.core.game.model.map.render;

import org.mech.rogue.game.render.map.Renderer;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.map.tile.TileConstants;
import org.mech.rougue.core.game.play.component.map.MapTerminalAdapter;
import org.mech.rougue.core.game.play.component.map.RenderedMapTile;
import org.mech.rougue.core.r.render.RenderId;
import org.mech.rougue.core.r.render.terminal.ColorConfigUtils;
import org.mech.rougue.core.r.render.terminal.DefaultTerminalConfigProvider;
import org.mech.rougue.core.r.render.terminal.TerminalCharConfig;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.geometry.Position;

public class SeenMapRenderer implements Renderer{

    public static final String SEE_ALL_SWITCH = "game.map.see.all";

    public void render(final Position at, final GameContext context, final MapTerminalAdapter mapTerminal) {
        final RenderedMapTile rTile = mapTerminal.get(at);

        if (rTile == null) {
            //propably terminal has changed in meantime and is not necessary to continue in rendering
            return;
        }

        final boolean seen = seePosition(context, at);

        if (seen) {
            render(mapTerminal, rTile, rTile.getTile().renderId(), at);
        } else {
            render(mapTerminal, rTile, getCoveredId(at), at);
        }


    }

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
