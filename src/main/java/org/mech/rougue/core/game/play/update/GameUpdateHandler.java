package org.mech.rougue.core.game.play.update;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.map.GameObject;

/**
 * Is handler that is game object aware of game update action
 * 
 * @author martin.krajc
 *
 */
public interface GameUpdateHandler extends GameObject {

	void update(GameContext gameContext);

	boolean isActive();

}
