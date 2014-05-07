package org.mech.rougue.core.game.play.component.panel;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.play.action.Action;
import org.mech.rougue.core.game.play.action.ui.CharacterPanelAction;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.ITerminal;
import org.mech.terminator.component.Alignment;
import org.mech.terminator.component.Label;
import org.mech.terminator.component.VerticleAlignment;
import org.mech.terminator.geometry.Dimension;

public class CharacterPanelComponent extends AcionPanel {

	@Inject
	private GameContext context;
	private Label nameLabel, attrlabel;

	@Inject
	private CharacterPanelAction action;

	@Override
	public void onRender(ITerminal terminal) {
		// terminal.bg(Color.red);
		final String name = context.getData().getPlayer().getName();

		String nameLabelString = "@yPlayer:\t@w" + name;
		nameLabel.setText(nameLabelString);
		super.onRender(terminal);
	}

	@Override
	protected void innerLayout() {
		setHeader("Player");

		setPrefferedSize(Dimension.of(30, -1));
		nameLabel = new Label("");
		attrlabel = new Label("test");
		attrlabel.setValignment(VerticleAlignment.CENTER);
		attrlabel.setAlignment(Alignment.CENTER);

		addComponent(nameLabel);
		addComponent(attrlabel);

	}

	@Override
	public Action getAction() {
		return action;
	}

	@Override
	public Alignment getAlignment() {
		return Alignment.RIGHT;
	}

}
