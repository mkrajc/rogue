package org.mech.rougue.core.game.state;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameState implements Serializable{
	private static final long serialVersionUID = -5553332920832912536L;

	private static final Logger LOG = LoggerFactory.getLogger(GameState.class);

	private boolean paused;
	private boolean turnFreezed;

	private final Map<String, Boolean> stateSwitchMap = new HashMap<String, Boolean>();

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(final boolean paused) {
		this.paused = paused;
		LOG.info("Game pause set to " + paused);
	}

	public boolean isTurnFreezed() {
		return turnFreezed;
	}

	public void setTurnFreezed(final boolean turnFreezed) {
		this.turnFreezed = turnFreezed;
		LOG.debug("Turn freeze set to " + turnFreezed);
	}

	public void setSwitch(final String key, final boolean value) {
		this.stateSwitchMap.put(key, value);
	}

	public boolean getSwitch(final String key, final boolean def) {
		return stateSwitchMap.containsKey(key) ? stateSwitchMap.get(key) : def;
	}

	public boolean getSwitch(final String key) {
		return getSwitch(key, false);
	}

	public void switchState(final String key) {
		final boolean value = getSwitch(key);
		final boolean newVal = !value;
		setSwitch(key, newVal);
		LOG.debug("State [" + key + "] set to [" + newVal + "]");
	}

}
