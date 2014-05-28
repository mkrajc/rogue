package org.mech.rougue.core.game;

import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.r.model.map.Map;

public class GameData {
	public Map map;
	public Player player;

	public Map getMap() {
		return map;
	}

	public Player getPlayer() {
		return player;
	}

	public void setMap(final Map map) {
		this.map = map;
	}

}
