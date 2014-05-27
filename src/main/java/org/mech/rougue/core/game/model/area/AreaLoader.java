package org.mech.rougue.core.game.model.area;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.map.MapStats;
import org.mech.rougue.core.r.event.EventBus;
import org.mech.rougue.core.r.event.LoadMapEvent;
import org.mech.rougue.core.r.event.RebuildLightEvent;
import org.mech.rougue.core.r.handler.register.BulkRegistration;
import org.mech.rougue.core.r.handler.register.NullRegistration;
import org.mech.rougue.core.r.handler.register.Registration;
import org.mech.rougue.core.r.handler.register.context.GObjectOnGameContextRegistrationHandler;
import org.mech.rougue.core.r.model.common.GObject;
import org.mech.rougue.core.r.model.map.loader.MapLoader;
import org.mech.rougue.core.r.object.GId;
import org.mech.rougue.core.r.object.GIdFactory;
import org.mech.rougue.core.r.render.tile.TileTheme;
import org.mech.rougue.factory.Inject;

public class AreaLoader implements GObject, LoadMapEvent.Handler {

	private final GId gid;

	@Inject
	private GameContext context;
	
	@Inject
	private TileTheme theme; 

	private Map<String, MapStats> cache = new HashMap<String, MapStats>();

	private Registration mapRegistration = new NullRegistration();

	private MapLoader mapLoader = new MapLoader();

	@PostConstruct
	public void setup() {
		context.add(this);
	}

	public AreaLoader() {
		gid = GIdFactory.next();
	}

	@Override
	public GId id() {
		return gid;
	}

	public org.mech.rougue.core.r.model.map.Map load(final GameContext context, final String id) {
		mapRegistration.unregister();

		final org.mech.rougue.core.r.model.map.Map old = context.getData().getMap();

		if (old != null && old.getMapId() != null && !old.getMapId().equals(id)) {
			cache.put(old.getMapId(), context.getData().getMap().getStats());
		}

		final org.mech.rougue.core.r.model.map.Map map = mapLoader.load(id);
		
		context.getData().setMap(map);

		// update stats
		final MapStats mapStats = cache.get(map.getMapId());

		if (mapStats != null) {
			context.getData().getMap().setStats(mapStats);
		}
		
		theme.setTheme(map.getArea().getTheme());

		mapRegistration = new BulkRegistration<GameContext, GObject>(context, map.getGameObjects(),
				new GObjectOnGameContextRegistrationHandler());

		mapRegistration.register();

		new RebuildLightEvent().fire(context);

		return map;
	}

	@Override
	public void registerHandlers(final EventBus bus) {
		bus.addHandler(LoadMapEvent.class, this);
	}

	@Override
	public void onAreaLoad(final LoadMapEvent event) {
		final String areaId = event.getAreaId();
		load(event.getContext(), areaId);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "#" + id();
	}

}
