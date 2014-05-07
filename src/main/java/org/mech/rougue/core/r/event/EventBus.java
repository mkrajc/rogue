package org.mech.rougue.core.r.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.event.Event.Handler;
import org.mech.rougue.core.r.handler.register.Registration;
import org.mech.rougue.core.r.handler.register.RegistrationDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventBus {

	private final GameContext owner;

	public EventBus(final GameContext context) {
		this.owner = context;
	}

	public interface Command {
		void execute();
	}

	protected static final Logger LOG = LoggerFactory.getLogger(EventBus.class);
	protected final Map<Class<?>, LinkedList<Event<?>>> handlersMap = new HashMap<Class<?>, LinkedList<Event<?>>>();
	protected final Map<Handler, Registration> registrations = new HashMap<Handler, Registration>();

	protected final LinkedList<Event<?>> queue = new LinkedList<Event<?>>();

	protected boolean update = false;
	protected int firingDepth = 0;
	protected boolean handlingQueue = false;

	protected List<Command> deferredDeltas;

	protected void push(final Event<?> event) {
		queue.add(event);
		event.onPush(this);
		LOG.debug("Event pushed to queue [event=" + event + ", queueSize=" + queue.size() + "]");
	}

	public <H extends Handler> void fire(final Event<H> event) {
		event.setContext(owner);

		if (!update) {
			push(event);
			return;
		}

		try {
			firingDepth++;

			final List<H> handlers = getHandlerList(event.getClass());
			final ListIterator<H> it = handlers.listIterator();

			while (it.hasNext()) {
				event.dispatch(it.next());
			}

		} finally {
			firingDepth--;
			if (firingDepth == 0) {
				handleQueuedAddsAndRemoves();
			}
		}
	}

	protected void handleQueuedAddsAndRemoves() {
		if (deferredDeltas != null) {
			try {
				for (final Command c : deferredDeltas) {
					c.execute();
				}
			} finally {
				deferredDeltas = null;
			}
		}
	}

	public void startUpdate() {
		this.update = true;
		while (!queue.isEmpty()) {
			fire(queue.poll());
		}
	}

	public void stopUpdate() {
		this.update = false;
	}

	@SuppressWarnings("unchecked")
	protected <H> List<H> getHandlerList(final Class<?> eventClass) {
		final LinkedList<Event<?>> sourceMap = handlersMap.get(eventClass);
		if (sourceMap == null) {
			return Collections.emptyList();
		}

		return (List<H>) sourceMap;
	}

	public <H extends Handler> Registration addHandler(final Class<?> eventClass, final H handler) {
		if (eventClass == null) {
			throw new NullPointerException("Cannot add a handler with a null event class");
		}
		if (handler == null) {
			throw new NullPointerException("Cannot add a null handler");
		}

		final Registration current = doRegister(eventClass, handler);
		RegistrationDispatcher reg = (RegistrationDispatcher) registrations.get(handler);

		if (reg == null) {
			reg = new RegistrationDispatcher();
			registrations.put(handler, reg);
		}

		reg.add(current);
		return current;
	}

	public <H extends Handler> void removeHandler(final H handler) {
		final Registration r = registrations.get(handler);
		if (r != null) {
			r.destroy();
			registrations.remove(handler);
		}
	}

	protected <H extends Handler> void doAdd(final Class<?> eventClass, final H handler) {
		if (firingDepth > 0) {
			enqueueAdd(eventClass, handler);
		} else {
			doAddNow(eventClass, handler);
		}
	}

	protected <H extends Handler> Registration doRegister(final Class<?> eventClass, final H handler) {
		final EventHandlerRegistration r = new EventHandlerRegistration(this, eventClass, handler);
		r.register();
		return r;
	}

	protected <H extends Handler> void doAddNow(final Class<?> eventClass, final H handler) {
		final List<H> l = ensureHandlerList(eventClass);
		l.add(handler);
	}

	@SuppressWarnings("unchecked")
	protected <H> List<H> ensureHandlerList(final Class<?> eventClass) {
		LinkedList<Event<?>> sourceList = handlersMap.get(eventClass);
		if (sourceList == null) {
			sourceList = new LinkedList<Event<?>>();
			handlersMap.put(eventClass, sourceList);
		}

		return (List<H>) sourceList;
	}

	protected <H> void doRemoveNow(final Class<?> eventClass, final H handler) {
		final List<H> l = getHandlerList(eventClass);

		final boolean removed = l.remove(handler);
		if (removed && l.isEmpty()) {
			prune(eventClass);
		}
	}

	protected <H extends Handler> void enqueueAdd(final Class<?> eventClass, final H handler) {
		defer(new Command() {
			@Override
			public void execute() {
				doAddNow(eventClass, handler);
			}
		});
	}

	protected <H extends Handler> void enqueueRemove(final Class<?> eventClass, final H handler) {
		defer(new Command() {
			@Override
			public void execute() {
				doRemoveNow(eventClass, handler);
			}
		});
	}

	protected void prune(final Class<?> eventClass) {
		final LinkedList<Event<?>> sourceList = handlersMap.get(eventClass);
		if (sourceList.isEmpty()) {
			handlersMap.remove(sourceList);
		}
	}

	protected void defer(final Command command) {
		if (deferredDeltas == null) {
			deferredDeltas = new ArrayList<Command>();
		}
		deferredDeltas.add(command);
	}

	protected <H extends Handler> void doRemove(final Class<?> eventClass, final H handler) {
		if (firingDepth > 0) {
			enqueueRemove(eventClass, handler);
		} else {
			doRemoveNow(eventClass, handler);
		}
	}

}
