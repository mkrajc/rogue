package org.mech.rougue.ui.component;

import java.awt.event.KeyEvent;
import org.mech.rougue.core.engine.handler.input.InputEvent;
import org.mech.rougue.core.engine.handler.input.InputHandlerAdapter;
import org.mech.terminator.component.MenuModel;

public class Menu extends InputHandlerAdapter {

	private final MenuModel model;

	public Menu(MenuModel menuModel) {
		this.model = menuModel;
	}

	public Menu() {
		this.model = new MenuModel();
	}

	@Override
	public void onInput(InputEvent event) {
		if (KeyEvent.VK_DOWN == event.getCode()) {
			model.next();
		} else if (KeyEvent.VK_UP == event.getCode()) {
			model.previous();
		} else if (KeyEvent.VK_ENTER == event.getCode()) {
			model.invokeMenuAction();
		} else {
			model.invokeMenuAction(event.getChar());
		}
	}

	public MenuModel getModel() {
		return model;
	}
}
