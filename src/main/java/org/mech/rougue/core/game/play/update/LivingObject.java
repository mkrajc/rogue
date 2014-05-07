package org.mech.rougue.core.game.play.update;

import org.mech.rougue.core.game.GameConstants;
import org.mech.rougue.core.game.GameContext;

public abstract class LivingObject implements Living {

	private int lifecycleSpeed = GameConstants.DEFAULT_LIVING_SPEED;

	private int currentTick = 0;
	private boolean dead = false;

	public void update(GameContext context) {
		if (!dead && currentTick++ == getLifecycleSpeed()) {
			onUpdate(context);
			currentTick = 0;
		}
	}

	protected abstract void onUpdate(GameContext context);

	@Override
	public int getLifecycleSpeed() {
		return lifecycleSpeed;
	}

	public void setLifecycleSpeed(int lifecycleSpeed) {
		this.lifecycleSpeed = lifecycleSpeed;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	@Override
	public boolean isActive() {
		return !isDead();
	}

}
