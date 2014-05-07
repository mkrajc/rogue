package org.mech.rougue.core.r.event;

import org.mech.rougue.core.r.event.RebuildLightEvent.Handler;

public class RebuildLightEvent extends Event<Handler> {

	public interface Handler extends Event.Handler {
		void onLightRebuild(final RebuildLightEvent event);
	}

	@Override
	protected void onDispatch(final Handler h) {
		h.onLightRebuild(this);
	}
}
