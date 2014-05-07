package org.mech.rougue.core.game.state;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameState {
	private static final Logger LOG = LoggerFactory.getLogger(GameState.class);

	private boolean paused;
	private boolean turnFreezed;

	private Map<String, Boolean> stateSwitchMap = new HashMap<String, Boolean>();

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
		LOG.debug("Game pause set to " + paused);
	}

	public boolean isTurnFreezed() {
		return turnFreezed;
	}

	public void setTurnFreezed(boolean turnFreezed) {
		this.turnFreezed = turnFreezed;
		LOG.debug("Turn freeze set to " + turnFreezed);
	}

	public void setSwitch(String key, boolean value) {
		this.stateSwitchMap.put(key, value);
	}

	public boolean getSwitch(String key, boolean def) {
		return stateSwitchMap.containsKey(key) ? stateSwitchMap.get(key) : def;
	}

	public boolean getSwitch(String key) {
		return getSwitch(key, false);
	}

	public void switchState(String key) {
		boolean value = getSwitch(key);
		boolean newVal = !value;
		setSwitch(key, newVal);
		LOG.debug("State [" + key + "] set to [" + newVal + "]");
	}

}
