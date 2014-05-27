package org.mech.rougue.core.game.state.impl;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.GameData;
import org.mech.rougue.core.game.model.area.AreaLoader;
import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.game.state.Loader;
import org.mech.rougue.core.r.event.LoadMapEvent;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.geometry.Position;

public class GameLoader implements Loader {

	@Inject
	private GameContext context;

	@Inject
	private AreaLoader areaLoader;

	@Inject
	private Player player;

	@Override
	public void load() {
		context.reset();

		player.setName("martin the best very long name pretty");
		player.setPosition(Position.at(5, 5));

		final GameData game = context.getData();
		game.setPlayer(player);

		new LoadMapEvent("test_1").fire(context);

		
	}

}
