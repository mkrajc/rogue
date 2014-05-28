package org.mech.rougue.core.game.state.impl;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.GameData;
import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.game.state.Loader;
import org.mech.rougue.core.r.model.map.loader.GameMapLoader;
import org.mech.rougue.core.r.model.player.move.PlayerMover;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.geometry.Position;

public class GameLoader implements Loader {

	@Inject
	private GameContext context;
	
	@Inject
	private GameMapLoader gameMapLoader;

	@Inject
	private Player player;

	@Override
	public void load() {
		context.reset();

		gameMapLoader.load(context, "test_1");
		
		final GameData game = context.getData();
		player.setName("martin the best very long name pretty");
		final Position startingPosition = Position.at(5, 5);
		player.setPosition(startingPosition);
		game.setPlayer(player);
		
		final PlayerMover playerMover = new PlayerMover();
		playerMover.placePlayer(context, player, startingPosition, game.getMap(), true);

		
	}

}
