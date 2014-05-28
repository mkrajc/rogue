package org.mech.rougue.core.r.model.player.loader;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.factory.Inject;

public class GamePlayerLoader {

	@Inject
	private GameContext context;

	private PlayerExporter playerExporter = new PlayerExporter();

	public Player load(final String id) {
		final Player player = playerExporter.load(id);
		player.setup(context);
		return player;
	}
}
