package org.mech.rougue.core.r.handler.game;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.model.common.GObject;

/**
 * Is handler that is game object aware of game update action
 * 
 * @author martin.krajc
 *
 */
public interface UpdateAwareGObject extends GObject {

	void update(GameContext gameContext);

}
