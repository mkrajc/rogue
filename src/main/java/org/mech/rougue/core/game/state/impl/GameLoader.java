package org.mech.rougue.core.game.state.impl;

import javax.annotation.PostConstruct;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.game.state.Loader;
import org.mech.rougue.core.r.event.EventBus;
import org.mech.rougue.core.r.event.LoadMapEvent;
import org.mech.rougue.core.r.event.player.PlayerChangeMapRequestEvent;
import org.mech.rougue.core.r.handler.register.BulkRegistration;
import org.mech.rougue.core.r.handler.register.NullRegistration;
import org.mech.rougue.core.r.handler.register.Registration;
import org.mech.rougue.core.r.handler.register.context.GObjectOnGameContextRegistrationHandler;
import org.mech.rougue.core.r.model.common.GObject;
import org.mech.rougue.core.r.model.map.Map;
import org.mech.rougue.core.r.model.map.loader.MapExporter;
import org.mech.rougue.core.r.model.player.loader.PlayerExporter;
import org.mech.rougue.core.r.model.player.move.PlayerMover;
import org.mech.rougue.core.r.object.GId;
import org.mech.rougue.core.r.object.GIdFactory;
import org.mech.rougue.core.r.render.tile.TileTheme;
import org.mech.rougue.factory.Inject;

public class GameLoader implements GObject, Loader, LoadMapEvent.Handler, PlayerChangeMapRequestEvent.Handler {

	private static final long serialVersionUID = -6235594730254161229L;

	private final GId gid = GIdFactory.next();

	@Inject
	private GameContext context;

	@Inject
	private TileTheme theme;

	private Registration mapRegistration = new NullRegistration();
	
	private PlayerExporter playerExporter = new PlayerExporter();
	private MapExporter mapExporter = new MapExporter();
	private StateExporter stateExporter = new StateExporter();

	@PostConstruct
	public void setup() {
		context.add(this);
	}

	@Override
	public void load() {
		context.reset();
		
		final State state = stateExporter.loadLast();

		loadMap(state.getMapId());
		loadPlayer(state.getPlayerId());
	}

	@Override
	public void registerHandlers(final EventBus bus) {
		bus.addHandler(PlayerChangeMapRequestEvent.class, this);
		bus.addHandler(LoadMapEvent.class, this);
	}

	@Override
	public void onMapLoad(final LoadMapEvent event) {
		loadMap(event.getMapId());
		autosave();
	}

	protected Map loadMap(final String id) {
		mapRegistration.unregister();

		final org.mech.rougue.core.r.model.map.Map map = mapExporter.load(id);

		context.data.map = map;
		theme.setTheme(map.getArea().getTheme());

		mapRegistration = new BulkRegistration<GameContext, GObject>(context, map.getGameObjects(), new GObjectOnGameContextRegistrationHandler());
		mapRegistration.register();

		return map;
	}
	
	protected Player loadPlayer(final String id) {
		final Player player = playerExporter.load(id);
		context.data.player = player;
		player.setup(context);
		
		final PlayerMover playerMover = new PlayerMover();
		playerMover.placePlayer(context, player, player.getPosition(), context.data.map, false);
		
		return player;
	}
	
	protected void autosave(){
		System.out.println("autosave...");
		
		mapExporter.save(context.data.map);
		playerExporter.save(context.data.player);
		
		final State state = new State();
		state.setMapId(context.data.map.getMapId());
		
		// TODO player
		state.setPlayerId("player");
		stateExporter.save(state);

	}

	@Override
	public GId id() {
		return gid;
	}

	@Override
	public void onPlayerChangeMapRequestEvent(final PlayerChangeMapRequestEvent event) {
		final PlayerMover mover = new PlayerMover();
		final Player player = event.getPlayer();

		// persist state of current map
		mover.displacePlayer(player, context.data.map);
		mapExporter.save(context.data.map);
		
		// load new map and place player on it
		loadMap(event.getToMapId());
		mover.placePlayer(event.getContext(), player, event.getDestinationPosition(), context.data.map, true);
		
		// persist state
		autosave();
	}

}
