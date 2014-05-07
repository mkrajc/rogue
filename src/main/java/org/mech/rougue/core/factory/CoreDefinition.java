package org.mech.rougue.core.factory;

import org.mech.rougue.Starter;
import org.mech.rougue.core.config.ui.provider.SimpleTerminalConfigProvider;
import org.mech.rougue.core.engine.Engine;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.area.AreaLoader;
import org.mech.rougue.core.game.model.area.AreaMapBuilder;
import org.mech.rougue.core.game.model.monster.Monster;
import org.mech.rougue.core.game.play.handler.GameInput;
import org.mech.rougue.core.game.play.handler.GameScreen;
import org.mech.rougue.core.game.play.handler.GameUpdate;
import org.mech.rougue.core.game.play.handler.PlayHandler;
import org.mech.rougue.core.game.start.action.StartGameAction;
import org.mech.rougue.core.game.start.action.StartQuitAction;
import org.mech.rougue.core.game.start.component.StartMenuComponent;
import org.mech.rougue.core.game.start.handler.StartHandler;
import org.mech.rougue.core.game.start.handler.StartScreen;
import org.mech.rougue.core.game.start.menu.StartMenu;
import org.mech.rougue.core.game.state.GameState;
import org.mech.rougue.core.game.state.impl.GameLoader;
import org.mech.rougue.core.game.update.move.MapMover;
import org.mech.rougue.core.r.context.GActions;
import org.mech.rougue.factory.AbstractDefinition;
import org.mech.rougue.lang.LocalizedResourceBundle;
import org.mech.terminator.screen.ScreenManager;

public class CoreDefinition extends AbstractDefinition {

	@Override
	public void definitions() {
		singleton(Engine.class);
		singleton(Starter.class);

		singleton(LocalizedResourceBundle.class);

		singleton(SimpleTerminalConfigProvider.class);
		singleton(GameLoader.class);

		singleton(GameScreen.class);
		singleton(GameInput.class);
		singleton(GameUpdate.class);
		singleton(PlayHandler.class);

		singleton(StartHandler.class);
		singleton(StartMenuComponent.class);
		singleton(StartScreen.class);
		singleton(StartMenu.class);
		singleton(StartQuitAction.class);
		singleton(StartGameAction.class);

		singleton(ScreenManager.class);

		singleton(MapMover.class);

		singleton(GameState.class);
		singleton(GameContext.class);
		singleton(GActions.class);

		factory(Monster.class);
		
		singleton(AreaLoader.class);
		singleton(AreaMapBuilder.class);
		

	}

}
