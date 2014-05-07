package org.mech.rougue.core.game.play.component.panel;

import org.mech.rougue.core.game.play.action.Action;
import org.mech.rougue.core.game.play.action.ActionMapping;
import org.mech.rougue.core.game.play.component.PlayPanel;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.ITerminal;
import org.mech.terminator.component.Alignment;
import org.mech.terminator.component.Label;
import org.mech.terminator.component.panel.VerticalPanel;
import org.mech.terminator.geometry.Dimension;

public abstract class AcionPanel extends VerticalPanel {

	private String header = "N/A";

	private Label headerLabel, escLabel;

	private static final String HEADER_FORMAT = "@g%s";
	private static final String ESCAPE_FORMAT = "@Gpress (@g%s@G) to hide";

	@Inject
	private ActionMapping actionMapping;

	public AcionPanel() {
		setPrefferedSize(Dimension.of(30, -1));

		headerLabel = new Label();
		headerLabel.setPrefferedSize(Dimension.of(-1, 1));
		headerLabel.setAlignment(Alignment.CENTER);
		addComponent(headerLabel);

		innerLayout();

		escLabel = new Label();
		escLabel.setAlignment(getAlignment() == Alignment.LEFT ? Alignment.LEFT : Alignment.RIGHT);
		escLabel.setPrefferedSize(Dimension.of(-1, 1));
		addComponent(escLabel);
	}

	public void update() {
		updateHeaderLabel();
		updateEscapeLabel();
	}

	protected abstract void innerLayout();

	public String getHeader() {
		return header;
	}

	public void setHeader(String name) {
		this.header = name;
		updateHeaderLabel();
	}

	private void updateHeaderLabel() {
		headerLabel.setText(String.format(HEADER_FORMAT, header));
	}

	private void updateEscapeLabel() {
		final String actionKey = actionMapping.getActionKey(getAction().getActionType());
		escLabel.setText(String.format(ESCAPE_FORMAT, actionKey.toLowerCase()));
	}

	public abstract Action getAction();

	public abstract Alignment getAlignment();

	public void show(PlayPanel playPanel) {
		if (isOrphan()) {
			playPanel.addComponent(this, getAlignment());
		} else {
			playPanel.removeComponent(this);
		}
	}

	@Override
	public void onRender(ITerminal terminal) {
		update();
		super.onRender(terminal);
	}

}
