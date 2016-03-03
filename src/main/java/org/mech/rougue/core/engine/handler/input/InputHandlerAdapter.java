package org.mech.rougue.core.engine.handler.input;

import java.awt.event.KeyEvent;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.mech.rougue.core.game.GameContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class InputHandlerAdapter implements InputListener, InputHandler {

	private final static Logger LOG = LoggerFactory.getLogger(InputHandlerAdapter.class);

	public ConcurrentLinkedQueue<InputEvent> events = new ConcurrentLinkedQueue<InputEvent>();

	public void queue(final KeyEvent event) {
		LOG.trace("queue input [" + event + "]");
		events.add(new InputEvent(event.getKeyCode()));
	}

	public InputEvent next() {
		return events.poll();
	}

	public boolean hasNext() {
		return events.peek() != null;
	}

	@Override
	public final void handleInput(final KeyEvent event) {
		queue(event);
	}

	@Override
	public void processInput(GameContext ctx) {
		while (hasNext()) {
			final InputEvent next = next();
			LOG.trace(getClass().getSimpleName() + " process input [" + next + "]");
			onInput(next, ctx);
		}

	}

	protected abstract void onInput(InputEvent event, GameContext ctx);
}
