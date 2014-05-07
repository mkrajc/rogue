package org.mech.rougue.core.game.model.area;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.area.converter.AreaPropertiesConverter;
import org.mech.rougue.core.game.model.map.MapStats;
import org.mech.rougue.core.game.model.map.decorator.Decorator;
import org.mech.rougue.core.game.model.map.tile.TileTheme;
import org.mech.rougue.core.r.event.EventBus;
import org.mech.rougue.core.r.event.LoadAreaEvent;
import org.mech.rougue.core.r.event.RebuildLightEvent;
import org.mech.rougue.core.r.handler.register.BulkRegistration;
import org.mech.rougue.core.r.handler.register.NullRegistration;
import org.mech.rougue.core.r.handler.register.Registration;
import org.mech.rougue.core.r.handler.register.context.GObjectOnGameContextRegistrationHandler;
import org.mech.rougue.core.r.model.common.GObject;
import org.mech.rougue.core.r.object.GId;
import org.mech.rougue.core.r.object.GIdFactory;
import org.mech.rougue.factory.Inject;

public class AreaLoader implements GObject, LoadAreaEvent.Handler {

	private final GId gid;

	@Inject
	private GameContext context;

	@Inject
	private AreaPropertiesConverter areaConverter;

	@Inject
	private AreaMapBuilder mapBuilder;

	@Inject
	private TileTheme theme;

	@Inject
	private Decorator decorator;

	Map<String, MapStats> cache = new HashMap<String, MapStats>();

	private Registration mapRegistration = new NullRegistration();

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

	public Area load(final GameContext context, final String id) {
		mapRegistration.unregister();

		final Area old = context.getData().getArea();

		if (old != null && old.getAreaId() != null && !old.getAreaId().equals(id)) {
			cache.put(old.getAreaId(), context.getData().getMap().getStats());
		}

		final Area area = doLoad(context, id);

		// update stats
		final MapStats mapStats = cache.get(area.getAreaId());

		if (mapStats != null) {
			context.getData().getMap().setStats(mapStats);
		}

		//		PlayerSight sight = context.getData().getPlayer().getSight();
		//		context.registerObject(sight);

		mapRegistration = new BulkRegistration<GameContext, GObject>(context, context.getData().getMap().getGameObjects(),
				new GObjectOnGameContextRegistrationHandler());

		mapRegistration.register();
		
		new RebuildLightEvent().fire(context);

		return area;
	}

	private Area doLoad(final GameContext context, final String id) {
		final Area area = areaConverter.load("maps/testmap.properties", id);
		mapBuilder.build(area);

		context.getData().setArea(area);

		loadTheme(context, area);
		return area;

	}

	private void loadTheme(final GameContext context, final Area area) {
		theme.setTheme(area.getTheme());
		decorator.decorate(area.getMap());
	}

	@Override
	public void registerHandlers(final EventBus bus) {
		bus.addHandler(LoadAreaEvent.class, this);
	}

	@Override
	public void onAreaLoad(final LoadAreaEvent event) {
		final String areaId = event.getAreaId();
		load(event.getContext(), areaId);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "#" + id();
	}

}
