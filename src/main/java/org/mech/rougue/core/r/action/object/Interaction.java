package org.mech.rougue.core.r.action.object;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.event.EventBus;
import org.mech.rougue.core.r.event.InteractionEvent;
import org.mech.rougue.factory.Inject;
import org.mech.rougue.utils.CollectionUtils;
import org.mech.rougue.utils.CollectionUtils.SelfPopulate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Interaction implements InteractionEvent.Handler {

	protected static final Logger LOG = LoggerFactory.getLogger(Interaction.class);

	@Inject
	public GameContext context;

	private final List<InteractiveObject> objects = new ArrayList<InteractiveObject>();

	private final Map<Class<?>, InteractionGroup> interactionMap = CollectionUtils
			.getSelfPopulatedMap(new SelfPopulate<Class<?>, InteractionGroup>() {

				@Override
				public InteractionGroup create(final Class<?> key) {
					return new InteractionGroup();
				}
			});

	@PostConstruct
	public void setup() {
		registerHandlers(context.eventBus);
	}

	@Override
	public void registerHandlers(final EventBus bus) {
		bus.addHandler(InteractionEvent.class, this);
	}

	@Override
	public void onInteractionAvailable(final InteractionEvent event) {
		LOG.debug("interaction available " + event);
		final InteractiveObject o = event.interactiveObject;
		objects.add(o);
	}

	@Override
	public void onInteractionUnavailable(final InteractionEvent event) {
		LOG.debug("interaction unavailable " + event);
		final InteractiveObject o = event.interactiveObject;
		objects.remove(o);
	}
	
	public Collection<InteractiveObject> getObjects() {
		return objects;
	}

	public Collection<InteractionGroup> getInteractionGroups() {
		interactionMap.clear();
		
		if(objects!=null){
			for(final InteractiveObject o : objects){
				final List<ObjectAction> actions = o.getActions();

				for (final ObjectAction action : actions) {
					final InteractionGroup interactionGroup = interactionMap.get(action.getClass());
					interactionGroup.dispatcher.add(action);
				}
			}
		}
		
		return interactionMap.values();
	}

}
