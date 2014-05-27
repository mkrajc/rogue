package org.mech.rougue.core.game;

import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.r.model.map.Map;

public class GameData {
	private Map map;
	private Player player;

	public Map getMap() {
		return map;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(final Player player) {
		this.player = player;
	}

	public void setMap(final Map map) {
		this.map = map;
	}

}
