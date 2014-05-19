package org.mech.rougue.core.r.action.object;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.event.EventBus;
import org.mech.rougue.core.r.event.PlayerMoveEvent;
import org.mech.rougue.core.r.handler.register.Registration;
import org.mech.rougue.core.r.model.geom.Positionable;
import org.mech.terminator.geometry.Position;

public abstract class PlayerMoveInteraction implements PlayerMoveEvent.Handler {

	private Registration interaction;
	protected final InteractiveObject interactiveObject;

	public PlayerMoveInteraction(final InteractiveObject interactiveObject) {
		this.interactiveObject = interactiveObject;
	}

	@Override
	public void registerHandlers(final EventBus bus) {}

	@Override
	public void onPlayerMove(final PlayerMoveEvent event) {
		if (isInteractionAvailable(event)) {
			refresh(event.getContext());
		} else {
			destroy();
		}

	}

	public void destroy() {
		if (interaction != null) {
			interaction.destroy();
			interaction = null;
		}
	}

	private void refresh(final GameContext context) {
		if (interaction == null) {
			interaction = new InteractionRegistration(context, interactiveObject);
			interaction.register();
		}
	}

	protected abstract boolean isInteractionAvailable(PlayerMoveEvent e);

	protected Position getItemPosition() {
		if (interactiveObject instanceof Positionable) {
			return ((Positionable) interactiveObject).getPosition();
		}
		return null;
	}
}
