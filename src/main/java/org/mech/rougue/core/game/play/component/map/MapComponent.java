package org.mech.rougue.core.game.play.component.map;

import java.util.Arrays;
import java.util.Collection;
import javax.annotation.PostConstruct;

import org.mech.rogue.game.render.map.MapRenderer;
import org.mech.rogue.game.render.map.ObjectRenderer;
import org.mech.rogue.game.render.map.Renderer;
import org.mech.rougue.core.engine.handler.render.RenderHandler;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.light.render.LightMaskRenderer;
import org.mech.rougue.core.game.model.map.render.DefaultMapObjectRenderer;
import org.mech.rougue.core.game.model.map.render.SeenMapRenderer;
import org.mech.rougue.factory.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import scala.collection.JavaConversions;
import scala.collection.immutable.List;

public class MapComponent implements RenderHandler {

    private static final Logger LOG = LoggerFactory.getLogger(MapComponent.class);

    @Inject
    private GameContext context;

    @Inject
    private SeenMapRenderer seenMapRenderer;

    @Inject
    private DefaultMapObjectRenderer objectRenderer;

    //	@Inject
    //	private GameInput gameInput;

    private MapRenderer mapRenderer;

    final MapTerminalAdapter mapTerminal = new MapTerminalAdapter();

    @PostConstruct
    public void setup() {
        java.util.List<Renderer> rList = Arrays.asList(
                seenMapRenderer,
                new ObjectRenderer(objectRenderer),
                new LightMaskRenderer());
        final List<Renderer> renderers = JavaConversions.asScalaBuffer(rList).toList();
        mapRenderer = new MapRenderer(renderers);
    }

    @Override
    public void render() {
        mapTerminal.adapt(context);

        mapRenderer.render(context, mapTerminal);

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
