package org.mech.rougue.core.game.play.action;

import org.mech.rougue.core.game.GameContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DefaultAction extends Action {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultAction.class);

	@Override
	protected void invoke(GameContext context) {
		if (!context.getState().isPaused()) {
			LOG.debug("action [" + getClass().getSimpleName() + "] invoked");
			doInvoke(context);
		}
	}

	protected abstract void doInvoke(GameContext ctx);

}
