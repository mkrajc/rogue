package org.mech.rougue.core.r.model.map.loader;

import javax.annotation.PostConstruct;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.event.EventBus;
import org.mech.rougue.core.r.event.LoadMapEvent;
import org.mech.rougue.core.r.handler.register.BulkRegistration;
import org.mech.rougue.core.r.handler.register.NullRegistration;
import org.mech.rougue.core.r.handler.register.Registration;
import org.mech.rougue.core.r.handler.register.context.GObjectOnGameContextRegistrationHandler;
import org.mech.rougue.core.r.model.common.GObject;
import org.mech.rougue.core.r.object.GId;
import org.mech.rougue.core.r.object.GIdFactory;
import org.mech.rougue.core.r.render.tile.TileTheme;
import org.mech.rougue.factory.Inject;

public class GameMapLoader implements GObject, LoadMapEvent.Handler {

	private static final long serialVersionUID = 876905982714781109L;

	private final GId gid;

	@Inject
	private GameContext context;
	
	@Inject
	private TileTheme theme; 

	private Registration mapRegistration = new NullRegistration();
	private MapExporter mapLoader = new MapExporter();

	@PostConstruct
	public void setup() {
		context.add(this);
	}

	public GameMapLoader() {
		gid = GIdFactory.next();
	}

	@Override
	public GId id() {
		return gid;
	}

	public org.mech.rougue.core.r.model.map.Map load(final GameContext context, final String id) {
		mapRegistration.unregister();

		final org.mech.rougue.core.r.model.map.Map old = context.getData().getMap();
		
		if(old != null){
			mapLoader.save(old);
		}

		final org.mech.rougue.core.r.model.map.Map map = mapLoader.load(id);

		context.getData().setMap(map);
		theme.setTheme(map.getArea().getTheme());

		mapRegistration = new BulkRegistration<GameContext, GObject>(context, map.getGameObjects(),
				new GObjectOnGameContextRegistrationHandler());
		mapRegistration.register();

		return map;
	}

	@Override
	public void registerHandlers(final EventBus bus) {
		bus.addHandler(LoadMapEvent.class, this);
	}

	@Override
	public void onMapLoad(final LoadMapEvent event) {
		load(event.getContext(), event.getMapId());
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "#" + id();
	}

}
