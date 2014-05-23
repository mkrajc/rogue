package org.mech.rougue.core.game.ui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;
import javax.annotation.PostConstruct;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.action.object.Interaction;
import org.mech.rougue.core.r.action.object.InteractionGroup;
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

		final Collection<InteractionGroup> iObjects = interactions.getInteractionGroups();

		if (CollectionUtils.isNotEmpty(iObjects)) {

			final Iterator<InteractionGroup> iterator = iObjects.iterator();
			while (iterator.hasNext()) {
				final InteractionGroup group = iterator.next();
				if (group.isGroup()) {
					this.add(createActionMenuItem(group.getGroupAction()));
					this.add(new Separator());
				}

				if (CollectionUtils.isNotEmpty(group.getInteractions())) {
					for (final ObjectAction a : group.getInteractions()) {
						this.add(createActionMenuItem(a));
					}
				}

				if (iterator.hasNext()) {
					this.add(new Separator());
				}

			}

			this.requestFocus();
			this.show(gameTerminalPanel, 10, 10);
		}
	}
	protected JMenuItem createActionMenuItem(final ObjectAction action) {
		final JMenuItem item = new JMenuItem(i18n.getMessage(action.getActionName(), i18n.getMessage(action.getObjectName())));
		item.addActionListener(new ActionAdapter(action));
		return item;
	}

	static class ActionAdapter implements ActionListener {

		private ObjectAction action;

		public ActionAdapter(final ObjectAction action) {
			this.action = action;
		}

		@Override
		public void actionPerformed(final ActionEvent e) {
			action.invoke();
		}

	}

}
