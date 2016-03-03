package org.mech.rougue.core.game.play.action;

import org.mech.rogue.game.context.State;
import org.mech.rougue.factory.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DefaultAction extends Action {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultAction.class);

	@Inject
	private State gameState;

	@Override
	protected void invoke() {
		if (!gameState.isPaused()) {
			LOG.debug("action [" + getClass().getSimpleName() + "] invoked");
			doInvoke();
		}
	}

	protected abstract void doInvoke();

}
