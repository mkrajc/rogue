package org.mech.rougue.core.engine;

import javax.annotation.PostConstruct;
import org.mech.rougue.core.game.GameConstants;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.play.component.map.MapComponent;
import org.mech.rougue.core.game.play.handler.GameInput;
import org.mech.rougue.core.game.play.handler.GameUpdate;
import org.mech.rougue.factory.Inject;
import org.mech.rougue.timer.TickHandler;
import org.mech.rougue.timer.TickTimer;
import org.mech.terminator.Terminal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Engine {

	private final static Logger LOG = LoggerFactory.getLogger(Engine.class);

	@Inject
	private MapComponent mapPanel;

	@Inject
	private GameInput gameInput;

	@Inject
	private GameUpdate gameUpdate;

	@Inject
	private GameContext gameContext;

	private TickTimer engineTimer;

	private boolean pause = false;

	@PostConstruct
	public void init() {
		engineTimer = new TickTimer("updater", new TickHandler() {

			@Override
			public void onTick(final double delta) {
				try {
					processInputs();
					update();
					render();
				} catch (final Exception e) {
					LOG.error("Exception occurred in tick", e);
					System.exit(1);
				}
			}

		}, GameConstants.GAME_TICKS_PER_SECONDS);
	}

	public void start() {
		LOG.info("engine started");
		engineTimer.start();
	}

	public void pause() {
		LOG.info("engine paused");
		engineTimer.pause();
	}

	public void resume() {
		LOG.info("engine resumed");
		engineTimer.resume();
	}

	public void processInputs() {
		gameInput.processInput(gameContext);
	}

	public void update() {
		gameUpdate.update();
	}

	public void render() {
		mapPanel.render();
		Terminal.getInstance().flush();
	}
}
