package org.mech.rougue.core.game.play.action.move;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.game.play.action.ActionDispatcher;
import org.mech.rougue.core.game.play.action.DefaultAction;
import org.mech.rougue.core.r.model.geom.Move;
import org.mech.rougue.core.r.model.player.move.PlayerMover;
import org.mech.rougue.factory.Inject;

public abstract class MoveAction extends DefaultAction {

	private PlayerMover mover = new PlayerMover();

	@Inject
	private GameContext context;

	@Inject
	private ActionDispatcher actionDispatcher;

	@Override
	protected void doInvoke() {
		
		final org.mech.rougue.core.r.model.map.Map map = context.getData().getMap();
		final Player player = context.getData().getPlayer();

		mover.movePlayer(context, player, getMove(), map);

	}

	protected abstract Move getMove();

}
