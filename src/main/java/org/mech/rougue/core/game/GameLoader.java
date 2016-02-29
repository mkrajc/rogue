package org.mech.rougue.core.game;

import javax.annotation.PostConstruct;

import org.mech.rogue.game.model.map.Map;
import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.r.event.EventBus;
import org.mech.rougue.core.r.event.LoadMapEvent;
import org.mech.rougue.core.r.event.RebuildLightEvent;
import org.mech.rougue.core.r.event.player.PlayerChangeMapRequestEvent;
import org.mech.rougue.core.r.export.state.State;
import org.mech.rougue.core.r.export.state.StateExporter;
import org.mech.rougue.core.r.handler.register.BulkRegistration;
import org.mech.rougue.core.r.handler.register.NullRegistration;
import org.mech.rougue.core.r.handler.register.Registration;
import org.mech.rougue.core.r.handler.register.context.GObjectOnGameContextRegistrationHandler;
import org.mech.rougue.core.r.model.common.GObject;

import org.mech.rougue.core.r.model.map.loader.MapExporter;
import org.mech.rougue.core.r.model.player.loader.PlayerExporter;
import org.mech.rougue.core.r.model.player.move.PlayerMover;
import org.mech.rougue.core.r.object.GId;
import org.mech.rougue.core.r.object.GIdFactory;
import org.mech.rougue.core.r.render.tile.TileTheme;
import org.mech.rougue.factory.Inject;

import scala.collection.JavaConversions$;

public class GameLoader implements GObject, LoadMapEvent.Handler, PlayerChangeMapRequestEvent.Handler {

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

	public void load() {
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

		final org.mech.rogue.game.model.map.Map map = mapExporter.load(id);

		context.data.map = map;
		theme.setTheme(map.area().getTheme());

		mapRegistration = new BulkRegistration<GameContext, GObject>(context, JavaConversions$.MODULE$.asJavaCollection(map.objects()), new GObjectOnGameContextRegistrationHandler());
		mapRegistration.register();

		return map;
	}
	
	protected Player loadPlayer(final String id) {
		context.remove(context.data.player);
		
		final Player player = playerExporter.load(id);
		context.data.player = player;
		
		context.add(player);
		
		final PlayerMover playerMover = new PlayerMover();
		playerMover.placePlayer(context, player, player.getPosition(), context.data.map, false);
		
		new RebuildLightEvent().fire(context);
		
		return player;
	}
	
	protected void autosave(){
		System.out.println("autosave...");
		
		mapExporter.save(context.data.map);
		playerExporter.save(context.data.player);
		
		final State state = new State();
		state.setMapId(context.data.map.mapId());
		
		// TODO player
		state.setPlayerId("player");
		stateExporter.save(state);

	}
	
	public void quicksave(){
		System.out.println("quicksave...");
		
		mapExporter.quicksave(context.data.map);
		playerExporter.quicksave(context.data.player);
		
		final State state = new State();
		state.setMapId(context.data.map.mapId());
		
		// TODO player
		state.setPlayerId("player");
		stateExporter.quicksave(state);

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

	public void quickload() {
		System.out.println("quickload...");
		load();
	}

}
