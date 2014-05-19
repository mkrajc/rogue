package org.mech.rougue.core.r.event;

import org.mech.rougue.core.r.event.ShowInteractionEvent.Handler;

public class ShowInteractionEvent extends Event<Handler> {

	public interface Handler extends Event.Handler {
		void onShowInteraction();
	}

	@Override
	protected void onDispatch(final Handler h) {
		h.onShowInteraction();
	}

}
