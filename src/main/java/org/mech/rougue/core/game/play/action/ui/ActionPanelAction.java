package org.mech.rougue.core.game.play.action.ui;

import org.mech.rougue.core.game.play.action.DefaultAction;
import org.mech.rougue.core.game.play.component.PlayPanel;
import org.mech.rougue.core.game.play.component.panel.AcionPanel;
import org.mech.rougue.factory.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ActionPanelAction extends DefaultAction {

	private static final Logger LOG = LoggerFactory.getLogger(ActionPanelAction.class);

	@Inject
	private PlayPanel playPanel;

	protected abstract AcionPanel getPanel();

	@Override
	protected void doInvoke() {
		LOG.debug(getClass().getSimpleName() + " show panel " + getPanel());
		getPanel().show(playPanel);
	}

}
