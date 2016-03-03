package org.mech.rougue.core.game.model.map.render;

import org.mech.rogue.game.render.map.RenderObject;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.play.component.map.MapTerminalAdapter;
import org.mech.rougue.core.r.render.terminal.DefaultTerminalConfigProvider;
import org.mech.rougue.factory.Inject;

public class DefaultMapObjectRenderer {
    @Inject
    private DefaultTerminalConfigProvider configProvider;

    public void render(RenderObject mapObject, GameContext context, MapTerminalAdapter mapTerminal) {
        RTileRenderer.render(configProvider, mapTerminal.get(mapObject.getPosition()), mapObject.getRenderId(), mapObject.getPosition());
    }

}
