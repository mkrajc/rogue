package org.mech.rougue.core.game.ui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.action.object.Interaction;
import org.mech.rougue.core.r.action.object.InteractiveObject;
import org.mech.rougue.core.r.action.object.ObjectAction;
import org.mech.rougue.core.r.event.EventBus;
import org.mech.rougue.core.r.event.ShowInteractionEvent;
import org.mech.rougue.factory.Inject;
import org.mech.rougue.lang.LocalizedResourceBundle;
import org.mech.rougue.ui.GameTerminalPanel;
import org.mech.rougue.utils.CollectionUtils;

public class InteractionPopup extends JPopupMenu implements ShowInteractionEvent.Handler {

	private static final long serialVersionUID = 3847040278124332014L;

	@Inject
	private GameTerminalPanel gameTerminalPanel;

	@Inject
	private GameContext context;

	@Inject
	private Interaction interactions;
	
	@Inject
	private LocalizedResourceBundle i18n;

	@PostConstruct
	public void setup() {
		gameTerminalPanel.setComponentPopupMenu(this);
		registerHandlers(context.eventBus);
	}

	@Override
	public void registerHandlers(final EventBus bus) {
		bus.addHandler(ShowInteractionEvent.class, this);
	}

	@Override
	public void onShowInteraction() {
		removeAll();

		final List<InteractiveObject> iObjects = interactions.getObjects();

		if (CollectionUtils.isNotEmpty(iObjects)) {
			for (final InteractiveObject interactiveObject : iObjects) {
				if (CollectionUtils.isNotEmpty(interactiveObject.getActions())) {
					for (final ObjectAction a : interactiveObject.getActions()) {
						final JMenuItem item = new JMenuItem(i18n.getMessage(a.getActionName(), i18n.getMessage(interactiveObject.toString())));
						item.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(final ActionEvent event) {
								a.invoke();
							}
						});
						this.add(item);
					}
				}
			}

			this.requestFocus();
			this.show(gameTerminalPanel, 10, 10);
		}

	}

}
