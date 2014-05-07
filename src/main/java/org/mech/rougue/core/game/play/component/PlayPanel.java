package org.mech.rougue.core.game.play.component;

import javax.annotation.PostConstruct;
import org.mech.rougue.core.game.play.component.map.MapComponent;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.component.Alignment;
import org.mech.terminator.component.Component;
import org.mech.terminator.component.panel.HorizontalPanel;
import org.mech.terminator.component.panel.SimplePanel;
import org.mech.terminator.component.panel.VerticalPanel;

public class PlayPanel extends SimplePanel {

	@Inject
	private MapComponent gameComponent;

	@Inject
	private MapLabel mapLabel;

	private HorizontalPanel mainPanel = new HorizontalPanel();

	@PostConstruct
	public void init() {
		mainPanel = new HorizontalPanel();
		final VerticalPanel layout = new VerticalPanel();

		layout.addComponent(gameComponent);
		layout.addComponent(mapLabel);

		mainPanel.addComponent(layout);

		addComponent(mainPanel);

	}

	public void addComponent(Component component, Alignment aligment) {
		if (aligment == Alignment.RIGHT) {
			mainPanel.addComponent(component);
			mainPanel.layout();
		}

		if (aligment == Alignment.LEFT) {
			mainPanel.insertComponent(component, 0);
			mainPanel.layout();
		}
	}

	public void removeComponent(Component component) {
		mainPanel.removeComponent(component);
		mainPanel.layout();
	}

}
