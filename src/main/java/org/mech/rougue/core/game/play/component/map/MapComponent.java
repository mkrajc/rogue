package org.mech.rougue.core.game.play.component.map;

import java.util.Arrays;
import java.util.Collection;
import javax.annotation.PostConstruct;

import org.mech.rogue.game.render.map.MapRenderer;
import org.mech.rogue.game.render.map.MapSceneRenderer;
import org.mech.rogue.game.render.map.ObjectRenderer;
import org.mech.rogue.game.render.map.Renderer;
import org.mech.rogue.game.render.map.SceneToPositionRenderer;
import org.mech.rogue.game.render.map.SeenMapRenderer;
import org.mech.rougue.core.engine.handler.render.RenderHandler;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.light.render.LightMaskRenderer;
import org.mech.rougue.core.r.render.terminal.DefaultTerminalConfigProvider;
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
    private DefaultTerminalConfigProvider configProvider;

    private MapRenderer mapRenderer;

    final MapTerminalAdapter mapTerminal = new MapTerminalAdapter();

    @PostConstruct
    public void setup() {
        final java.util.List<Renderer> rList = Arrays.asList(
                new SeenMapRenderer(configProvider),
                new LightMaskRenderer());
        final List<Renderer> renderers = JavaConversions.asScalaBuffer(rList).toList();
        final java.util.List<MapSceneRenderer> sceneRendererList = Arrays.asList(
                new SceneToPositionRenderer(renderers),
                new ObjectRenderer(configProvider));
        final List<MapSceneRenderer> sceneRenderers = JavaConversions.asScalaBuffer(sceneRendererList).toList();
        this.mapRenderer = new MapRenderer(sceneRenderers);
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

}
