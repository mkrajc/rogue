package org.mech.rougue.core.game.model.monster;

import org.mech.rogue.game.action.map.MapMovement;
import org.mech.rogue.game.action.map.NormalMapMovement;
import org.mech.rogue.game.render.map.Normal$;
import org.mech.rogue.game.render.map.RenderObject;
import org.mech.rogue.game.render.map.RenderOption;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.model.common.LiveObject;
import org.mech.rougue.core.r.object.GId;
import org.mech.rougue.core.r.render.RenderId;
import org.mech.terminator.geometry.Position;

import scala.Option;

public class Monster extends LiveObject implements RenderObject {

	private GId gId;
	private final RenderId id = new RenderId(getType());
	private Position position;
	private boolean updated;

	private final MapMovement mapMoveVisitor = new NormalMapMovement();


	@Override
	protected void onUpdate(final GameContext context) {
		final Position playerPosition = context.getData().getPlayer().getPosition();
		final int testX = getPosition().x;
		final int testY = getPosition().y;

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

		Option<Position> newPosition = mapMoveVisitor.move(getPosition(), newMonsterPosition, context.getData().getMap());
		System.out.println("new position " + newPosition);

		if (getPosition().equals(playerPosition)) {
			System.err.println("GAME OVER from Monster " + this);
		}

	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public void setPosition(final Position position) {
		this.position = position;
		this.updated = true;
	}

	@Override
	public RenderOption getRenderOptions() {
		return Normal$.MODULE$;
	}

	public String getType() {
		return "monster";
	}

	public boolean isUpdated() {
		return updated;
	}

	public void setUpdated(final boolean state) {
		this.updated = state;
	}

	@Override
	public RenderId getRenderId() {
		return id;
	}

	@Override
	public GId id() {
		return gId;
	}

}
