package org.mech.rougue.core.game.model.monster;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.map.render.MapObject;
import org.mech.rougue.core.game.model.map.render.RenderId;
import org.mech.rougue.core.game.play.update.LivingObject;
import org.mech.rougue.core.game.start.handler.StartHandler;
import org.mech.rougue.core.game.update.move.MapMover;
import org.mech.rougue.core.r.object.GId;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.geometry.Position;

public class Monster extends LivingObject implements MapObject {

	private RenderId id;
	private Position position;
	private boolean updated;

	@Inject
	private MapMover mapMoveVisitor;

	@Inject
	private StartHandler startHandler;

	@Override
	protected void onUpdate(GameContext context) {
		final Position playerPosition = context.getData().getPlayer().getPosition();
		int testX = getPosition().x;
		int testY = getPosition().y;

		int x = 1;
		if (playerPosition.x == testX) {
			x = 0;
		} else if (playerPosition.x < testX) {
			x = -1;
		}

		int y = 1;
		if (playerPosition.y == testY) {
			y = 0;
		} else if (playerPosition.y < testY) {
			y = -1;
		}

		final Position newMonsterPosition = getPosition().addXY(x, y);

//		mapMoveVisitor.move(this, newMonsterPosition, context.getData().getCurrentMap());

		if (getPosition().equals(playerPosition)) {
			System.err.println("GAME OVER from Monster " + this);
			startHandler._switch_();
		}

	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public void setPosition(Position position) {
		this.position = position;
		this.updated = true;
	}

	@Override
	public int getRenderOptions() {
		return 0;
	}

	@Override
	public String getType() {
		return "monster";
	}

	public boolean isUpdated() {
		return updated;
	}

	public void setUpdated(boolean state) {
		this.updated = state;
	}

	@Override
	public RenderId getRenderId() {
		return id;
	}

	@Override
	public GId id() {
		// TODO Auto-generated method stub
		return null;
	}

}
