package org.mech.rougue.core.factory;

import org.mech.rougue.core.game.play.component.MapLabel;
import org.mech.rougue.core.game.play.component.PlayPanel;
import org.mech.rougue.core.game.play.component.dialog.PauseDialog;
import org.mech.rougue.core.game.play.component.map.MapComponent;
import org.mech.rougue.core.game.play.component.panel.CharacterPanelComponent;
import org.mech.rougue.core.game.play.component.panel.OtherComponentScreenPanel;
import org.mech.rougue.factory.AbstractDefinition;

public class ComponentDefinition extends AbstractDefinition {

	@Override
	public void definitions() {
		singleton(MapComponent.class);
		singleton(CharacterPanelComponent.class);
		singleton(OtherComponentScreenPanel.class);
		singleton(PauseDialog.class);
		singleton(MapLabel.class);
		singleton(PlayPanel.class);
	}

}
