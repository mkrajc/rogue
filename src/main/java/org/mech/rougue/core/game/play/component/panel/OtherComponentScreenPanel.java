package org.mech.rougue.core.game.play.component.panel;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.play.action.Action;
import org.mech.rougue.core.game.play.action.ui.OtherScreenPanelAction;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.ITerminal;
import org.mech.terminator.component.Alignment;
import org.mech.terminator.component.Label;
import org.mech.terminator.ui.TextGraphics;

public class OtherComponentScreenPanel extends AcionPanel {

	@Inject
	private GameContext context;
	private Label nameLabel;
	private Label areaLabel;
	private Label roomLabel;

	@Inject
	private OtherScreenPanelAction action;

	@Override
	public void onRender(ITerminal terminal) {
		final TextGraphics graphics = new TextGraphics(terminal);
		
		final int seen = context.getData().getMap().getStats().getSeenSize();

		String nameLabelString = "@wSeen:\t@G" + seen;
		nameLabel.setText(nameLabelString);
		nameLabel.packHeight(graphics);

		String areaText = "@wArea:\t@G" + context.getData().getAreaStats().getAreaName();
		areaLabel.setText(areaText);
		areaLabel.packHeight(graphics);
		
		String roomText = "@wRoom:\t@G" + context.getData().getAreaStats().getRoomName();
		roomLabel.setText(roomText);
//		roomLabel.packHeight(graphics);
		
		layout();
		
		super.onRender(terminal);
	}

	@Override
	protected void innerLayout() {
		setHeader("Other");

		nameLabel = new Label();
		addComponent(nameLabel);

		areaLabel = new Label();
		addComponent(areaLabel);
		
		roomLabel = new Label();
		addComponent(roomLabel);
	}

	@Override
	public Action getAction() {
		return action;
	}

	@Override
	public Alignment getAlignment() {
		return Alignment.LEFT;
	}
	
	

}
