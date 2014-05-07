package org.mech.rougue.core.game.start.component;

import java.awt.Color;
import javax.annotation.PostConstruct;
import org.mech.rougue.core.game.start.menu.StartMenu;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.ITerminal;
import org.mech.terminator.component.Component;
import org.mech.terminator.component.MenuModel.Choice;
import org.mech.terminator.ui.TextGraphics;

public class StartMenuComponent extends Component {

	@Inject
	private StartMenu menu;

	@PostConstruct
	public void setupMenu() {
		addInputListener(menu);
		focus();
	}

	@Override
	public void onRender(ITerminal terminal) {
		TextGraphics graphics = new TextGraphics(terminal);
		int choiceSize = menu.getModel().getChoiceCount();
		int selected = menu.getModel().getSelectedChoiceIndex();
		for (int i = 0; i < choiceSize; i++) {
			final Choice choice = menu.getModel().getChoice(i);

			if (selected == i) {
				graphics.setFgColor(Color.CYAN);
			} else {
				graphics.setFgColor(null);
			}

			graphics.drawString(choice.getText());
			graphics.newLine();
		}

	}

}
