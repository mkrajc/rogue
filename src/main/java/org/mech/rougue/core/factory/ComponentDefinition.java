package org.mech.rougue.core.factory;

import org.mech.rougue.core.game.play.component.map.MapComponent;
import org.mech.rougue.core.game.ui.PlayerStatsPanel;
import org.mech.rougue.factory.AbstractDefinition;
import org.mech.rougue.ui.GameTerminalPanel;

public class ComponentDefinition extends AbstractDefinition {

	@Override
	public void definitions() {
		singleton(GameTerminalPanel.class);
		singleton(MapComponent.class);
		singleton(PlayerStatsPanel.class);
	}

}
