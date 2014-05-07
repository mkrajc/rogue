package org.mech.rougue.core.engine;

import javax.annotation.PostConstruct;
import org.mech.rougue.core.engine.handler.Handler;
import org.mech.rougue.core.game.GameConstants;
import org.mech.rougue.timer.TickHandler;
import org.mech.rougue.timer.TickTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Engine {

	private final static Logger LOG = LoggerFactory.getLogger(Engine.class);

	private TickTimer engineTimer;

	private Handler handler;

	@PostConstruct
	public void init() {
		engineTimer = new TickTimer("updater", new TickHandler() {

			@Override
			public void onTick(double delta) {
				try {
					processInputs();
					update();
					render();
				} catch (Exception e) {
					LOG.error("Exception occurred in tick", e);
					System.exit(1);
				}
			}

		}, GameConstants.GAME_TICKS_PER_SECONDS);
	}

	public void start() {
		engineTimer.start();
	}

	public void processInputs() {
		handler.processInput();
	}

	public void update() {
		handler.update();
	}

	public void render() {
		handler.render();
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

}
