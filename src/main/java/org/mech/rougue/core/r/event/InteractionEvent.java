package org.mech.rougue.core.r.event;

import org.mech.rougue.core.r.action.object.InteractiveObject;

public class InteractionEvent extends Event<InteractionEvent.Handler> {

	public interface Handler extends Event.Handler {
		void onInteractionAvailable(InteractionEvent event);
		void onInteractionUnavailable(InteractionEvent event);
	}

	public long created;
	public InteractiveObject interactiveObject;
	private final boolean available;

	public InteractionEvent(final InteractiveObject iObject, final boolean available) {
		this.interactiveObject = iObject;
		this.created = System.currentTimeMillis();
		this.available = available;
	}

	@Override
	protected void onDispatch(final Handler h) {
		if (available) {
			h.onInteractionAvailable(this);
		} else {
			h.onInteractionUnavailable(this);
		}
	}

}
