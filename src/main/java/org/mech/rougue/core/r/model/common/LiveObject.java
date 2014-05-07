package org.mech.rougue.core.r.model.common;

import org.mech.rougue.core.game.GameConstants;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.handler.game.UpdateAwareGObject;

public abstract class LiveObject implements UpdateAwareGObject {
	private int lifecycleSpeed = GameConstants.DEFAULT_LIVING_SPEED;

	private int currentTick = 0;
	private boolean dead = false;

	@Override
	public void update(final GameContext context) {
		if (!dead && currentTick++ == getLifecycleSpeed()) {
			onUpdate(context);
			currentTick = 0;
		}
	}

	protected abstract void onUpdate(GameContext context);

	public int getLifecycleSpeed() {
		return lifecycleSpeed;
	}

	public void setLifecycleSpeed(final int lifecycleSpeed) {
		this.lifecycleSpeed = lifecycleSpeed;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(final boolean dead) {
		this.dead = dead;
	}

}
