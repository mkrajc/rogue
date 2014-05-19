package org.mech.rougue.core.game.model.map.render;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.map.Map;
import org.mech.rougue.core.game.play.component.map.MapTerminalAdapter;
import org.mech.rougue.core.r.handler.game.light.LightMask;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.geometry.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("rawtypes")
public class MapObjectOrdererRenderer extends AbstractOrderedMapRenderer {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractOrderedMapRenderer.class);

	@Inject
	private List<MapObjectRenderer> renderers;

	private final java.util.Map<String, MapObjectRenderer> map = new HashMap<String, MapObjectRenderer>();

	@PostConstruct
	public void setup() {
		for (final MapObjectRenderer objectRenderer : renderers) {
			map.put(objectRenderer.getType(), objectRenderer);
		}
	}

	@Override
	public void render(final GameContext context, final MapTerminalAdapter mapTerminal) {
		final List<MapObject> mapObjects = context.getGameObjects(MapObject.class);
		
		// player must go last
		Collections.reverse(mapObjects);

		for (final MapObject mapObject : mapObjects) {
			renderMapObject(mapObject, context, mapTerminal);
		}
	}
	
	private void renderMapObject(final MapObject mapObject, final GameContext context, final MapTerminalAdapter mapTerminal){
		final Map cMap = context.getData().getMap();
		final LightMask lightMask = context.getGameObject(LightMask.class);
		if (isOnScreen(context, mapObject, mapTerminal)) {
			final Position at = mapObject.getPosition();
			final boolean memorable = (mapObject.getRenderOptions() & RenderOptions.MEMORABLE) == RenderOptions.MEMORABLE;
			final boolean fixed = (mapObject.getRenderOptions() & RenderOptions.FIXED) == RenderOptions.FIXED;
			final boolean invisible = (mapObject.getRenderOptions() & RenderOptions.INVISIBLE) == RenderOptions.INVISIBLE;

			boolean render = false;
			
			if(invisible){
				return;
			}

			if (memorable) {
				if (cMap.getStats().seen(at)) {
					dispatch(mapObject, context, mapTerminal);
					render = true;
				}
			}

			if (!render && fixed) {
				dispatch(mapObject, context, mapTerminal);
				render = true;
			}

			if (!render && lightMask.isLighten(at)) {
				dispatch(mapObject, context, mapTerminal);
				render = true;
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void dispatch(final MapObject mapObject, final GameContext context, final MapTerminalAdapter mapTerminal) {
		MapObjectRenderer mapObjectRenderer = map.get(mapObject.getType());

		if (mapObjectRenderer == null) {
			mapObjectRenderer = map.get(null);
		}

		if (mapObjectRenderer == null) {
			throw new IllegalArgumentException("No renderer found for id=" + mapObject.getType());
		}

		LOG.trace("dispatching to [" + mapObjectRenderer + "]");
		mapObjectRenderer.render(mapObject, context, mapTerminal);
	}

	private boolean isOnScreen(final GameContext context, final MapObject mapObject, final MapTerminalAdapter mapTerminal) {
		return mapTerminal.toTerminal(mapObject.getPosition()) != null && context.getData().getMap().getStats().seen(mapObject.getPosition());
	}

	@Override
	public int getOrder() {
		return RenderOrder.OBJECTS.ordinal();
	}
}
