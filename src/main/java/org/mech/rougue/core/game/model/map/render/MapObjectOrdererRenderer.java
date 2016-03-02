package org.mech.rougue.core.game.model.map.render;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;

import org.mech.rogue.game.model.map.Map;
import org.mech.rogue.game.render.map.Fixed$;
import org.mech.rogue.game.render.map.Invisible$;
import org.mech.rogue.game.render.map.Memorable$;
import org.mech.rogue.game.render.map.Normal$;
import org.mech.rogue.game.render.map.RenderObject;
import org.mech.rogue.game.render.map.RenderOption;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.game.play.component.map.MapTerminalAdapter;
import org.mech.rougue.core.r.handler.game.light.LightMask;
import org.mech.rougue.core.r.object.GObjectUtils;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.geometry.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("rawtypes")
public class MapObjectOrdererRenderer extends AbstractOrderedMapRenderer {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractOrderedMapRenderer.class);

    @Inject
    private List<MapObjectRenderer> renderers;

    private final java.util.Map<Class<?>, MapObjectRenderer> map = new HashMap<Class<?>, MapObjectRenderer>();
    private final java.util.Map<RenderOption, RenderStrategy> renderStrategyMap = new HashMap<>();

    @PostConstruct
    public void setup() {
        System.out.println("renderers: " + renderers.size());
        for (final MapObjectRenderer objectRenderer : renderers) {
            System.out.println("renderer add: " + objectRenderer);
            map.put(objectRenderer.getClass(), objectRenderer);
        }

        renderStrategyMap.put(Normal$.MODULE$, new Normal());
        renderStrategyMap.put(Memorable$.MODULE$, new Memo());
        renderStrategyMap.put(Fixed$.MODULE$, new Fixed());
        renderStrategyMap.put(Invisible$.MODULE$, new Invisible());
    }

    @Override
    public void render(final GameContext context, final MapTerminalAdapter mapTerminal) {
        final List<RenderObject> mapObjects = context.getGameObjects(RenderObject.class);

        // player must go last
        Collections.reverse(mapObjects);

        final Player player = GObjectUtils.getObjectOfType(mapObjects, Player.class);
        mapObjects.remove(player);

        for (final RenderObject mapObject : mapObjects) {
            renderMapObject(mapObject, context, mapTerminal);
        }

        renderMapObject(player, context, mapTerminal);
    }

    private void renderMapObject(final RenderObject mapObject, final GameContext context, final MapTerminalAdapter mapTerminal) {
        if (isOnScreen(context, mapObject, mapTerminal)) {
            renderStrategyMap.get(mapObject.getRenderOptions()).render(mapObject, context, mapTerminal);
        }
    }

    private interface RenderStrategy {
        boolean render(RenderObject obj, GameContext context, MapTerminalAdapter mapTerminal);
    }

    private class Memo implements RenderStrategy {
        public boolean render(RenderObject obj, GameContext context, MapTerminalAdapter mapTerminal) {
            final Map cMap = context.getData().getMap();
            final Position at = obj.getPosition();
            if (cMap.stats().seen(at)) {
                dispatch(obj, context, mapTerminal);
                return true;
            }
            return false;
        }
    }

    private class Normal implements RenderStrategy {
        public boolean render(RenderObject obj, GameContext context, MapTerminalAdapter mapTerminal) {
            final LightMask lightMask = context.getLightMask();
            final Position at = obj.getPosition();
            if (lightMask.isLighten(at)) {
                dispatch(obj, context, mapTerminal);
                return true;
            }
            return false;
        }
    }

    private class Fixed implements RenderStrategy {
        public boolean render(RenderObject obj, GameContext context, MapTerminalAdapter mapTerminal) {
            dispatch(obj, context, mapTerminal);
            return true;
        }
    }

    private class Invisible implements RenderStrategy {
        public boolean render(RenderObject obj, GameContext context, MapTerminalAdapter mapTerminal) {
            return false;
        }
    }


    @SuppressWarnings("unchecked")
    private void dispatch(final RenderObject mapObject, final GameContext context, final MapTerminalAdapter mapTerminal) {
        MapObjectRenderer mapObjectRenderer = map.get(mapObject.getClass());

        if (mapObjectRenderer == null) {
            mapObjectRenderer = map.get(DefaultMapObjectRenderer.class);
        }

        if (mapObjectRenderer == null) {
            throw new IllegalArgumentException("No renderer found for id=" + mapObject);
        }

        mapObjectRenderer.render(mapObject, context, mapTerminal);
    }

    private boolean isOnScreen(final GameContext context, final RenderObject mapObject, final MapTerminalAdapter mapTerminal) {
        return mapTerminal.toTerminal(mapObject.getPosition()) != null && context.getData().getMap().stats().seen(mapObject.getPosition());
    }

    @Override
    public int getOrder() {
        return RenderOrder.OBJECTS.ordinal();
    }
}
