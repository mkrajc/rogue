package org.mech.rougue.core.game.model.player;

import org.mech.rogue.game.export.Exportable;
import org.mech.rogue.game.render.map.Fixed$;
import org.mech.rogue.game.render.map.RenderObject;
import org.mech.rogue.game.render.map.RenderOption;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.context.ContextAwareGObject;
import org.mech.rougue.core.r.event.player.PlayerDiedEvent;
import org.mech.rougue.core.r.handler.game.UpdateAwareGObject;
import org.mech.rougue.core.r.handler.game.player.PlayerSight;
import org.mech.rougue.core.r.model.combat.Combatant;
import org.mech.rougue.core.r.model.combat.CombatantStats;
import org.mech.rougue.core.r.model.combat.IsCombatant;
import org.mech.rougue.core.r.model.inv.Equipment;
import org.mech.rougue.core.r.model.inv.Inventory;
import org.mech.rougue.core.r.model.player.stat.PlayerStats;
import org.mech.rougue.core.r.object.GId;
import org.mech.rougue.core.r.object.GIdFactory;
import org.mech.rougue.core.r.render.RenderId;
import org.mech.terminator.geometry.Position;

public class Player implements RenderObject, IsCombatant, UpdateAwareGObject, Exportable, ContextAwareGObject {

	private static final long serialVersionUID = -1887109198236195102L;
	private final GId gId;
	private final RenderId renderId;

	private String name;
	private Position position;

	public Inventory inventory;
	public Equipment equipment;
	public Combatant combatant;

	private PlayerStats stats;
	private PlayerSight sight;

	public Player() {
		renderId = new RenderId(getType());
		gId = GIdFactory.next();

		sight = new PlayerSight(this);
		inventory = new Inventory();
		equipment = new Equipment();
		stats = new PlayerStats();
		combatant = new Combatant(this);
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
	public RenderOption getRenderOptions() {
		return Fixed$.MODULE$;
	}

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

	@Override
	public void onAdd(final GameContext context) {
		context.add(sight);
	}


	@Override
	public void onRemove(final GameContext context) {
		context.remove(sight);
	}

	@Override
	public String objectId() {
		return "player";
	}
}
