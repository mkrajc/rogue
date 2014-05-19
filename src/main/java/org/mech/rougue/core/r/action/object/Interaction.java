package org.mech.rougue.core.r.action.object;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.event.EventBus;
import org.mech.rougue.core.r.event.InteractionEvent;
import org.mech.rougue.factory.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Interaction implements InteractionEvent.Handler {
	
	protected static final Logger LOG = LoggerFactory.getLogger(Interaction.class);
	
	@Inject
	public GameContext context;
	
	private final List<InteractiveObject> objects = new ArrayList<InteractiveObject>();
	
	@PostConstruct
	public void setup(){
		registerHandlers(context.eventBus);
	}

	@Override
	public void registerHandlers(final EventBus bus) {
		bus.addHandler(InteractionEvent.class, this);
	}

	@Override
	public void onInteractionAvailable(final InteractionEvent event) {
		LOG.debug("interaction available " + event);
		objects.add(event.interactiveObject);
	}

	@Override
	public void onInteractionUnavailable(final InteractionEvent event) {
		LOG.debug("interaction unavailable " + event);
		objects.remove(event.interactiveObject);
	}

	public List<InteractiveObject> getObjects() {
		return objects;
	}
	
}
