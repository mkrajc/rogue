package org.mech.rougue.core.game.model.player;

import javax.annotation.PostConstruct;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.map.render.MapObject;
import org.mech.rougue.core.game.model.map.render.RenderId;
import org.mech.rougue.core.game.model.map.render.RenderOptions;
import org.mech.rougue.core.r.event.player.PlayerDiedEvent;
import org.mech.rougue.core.r.handler.game.UpdateAwareGObject;
import org.mech.rougue.core.r.handler.game.player.PlayerSight;
import org.mech.rougue.core.r.model.combat.Combatant;
import org.mech.rougue.core.r.model.combat.CombatantStats;
import org.mech.rougue.core.r.model.combat.IsCombatant;
import org.mech.rougue.core.r.model.inv.Inventory;
import org.mech.rougue.core.r.model.player.stat.PlayerStats;
import org.mech.rougue.core.r.object.GId;
import org.mech.rougue.core.r.object.GIdFactory;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.geometry.Position;

public class Player implements MapObject, IsCombatant, UpdateAwareGObject {

	private final GId gId;
	private final RenderId renderId;

	private String name;
	private Position position;

	public Inventory inventory;
	public Combatant combatant;

	private PlayerStats stats;

	@Inject
	private PlayerSight sight;

	@Inject
	private GameContext gContext;

	public Player() {
		renderId = new RenderId(getType());
		gId = GIdFactory.next();

		inventory = new Inventory();
		stats = new PlayerStats();
		combatant = new Combatant(this);
	}

	@PostConstruct
	public void setup() {
		gContext.add(this);
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public void setPosition(final Position position) {
		this.position = position;
	}

	public PlayerSight getSight() {
		return sight;
	}

	public void setSight(final PlayerSight sight) {
		this.sight = sight;
	}

	@Override
	public int getRenderOptions() {
		return 0 | RenderOptions.FIXED;
	}

	@Override
	public String getType() {
		return "player";
	}

	@Override
	public RenderId getRenderId() {
		return renderId;
	}

	@Override
	public GId id() {
		return gId;
	}

	public PlayerStats getStats() {
		return stats;
	}

	public void setStats(final PlayerStats stats) {
		this.stats = stats;
	}

	@Override
	public CombatantStats getCombatStats() {
		return stats.combatantStats;
	}
	
	@Override
	public void update(final GameContext gameContext) {
		// check hitpoints
		if(stats.combatantStats.getHitPoints().getValue().intValue() <= 0){
			// died
			gameContext.eventBus.fire(new PlayerDiedEvent(this));
		}
	}

}
