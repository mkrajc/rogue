package org.mech.rougue.core.r.event;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.event.Event.Handler;
import org.slf4j.LoggerFactory;

public abstract class Event<H extends Handler> {

	public interface Handler {
		void registerHandlers(EventBus bus);
	}

	private static int idCounter = 0;

	private final int id = idCounter++;
	private GameContext context;

	protected void dispatch(final H h) {
		LoggerFactory.getLogger(getClass()).debug("Event dispatched [event=" + this + ", handler=" + h + "]");
		onDispatch(h);
	}

	protected abstract void onDispatch(final H h);

	protected void onPush(final EventBus bus) {
		LoggerFactory.getLogger(getClass()).debug("Event pushed [event=" + this + "]");
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "#" + id;
	}
	
	public void fire(final GameContext context){
		context.eventBus.fire(this);
	}

	public GameContext getContext() {
		return context;
	}

	public void setContext(final GameContext context) {
		this.context = context;
	}

}
