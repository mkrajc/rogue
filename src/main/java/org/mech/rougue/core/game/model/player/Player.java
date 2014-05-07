package org.mech.rougue.core.game.model.player;

import javax.annotation.PostConstruct;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.map.render.MapObject;
import org.mech.rougue.core.game.model.map.render.RenderId;
import org.mech.rougue.core.game.model.map.render.RenderOptions;
import org.mech.rougue.core.r.handler.game.player.PlayerSight;
import org.mech.rougue.core.r.object.GId;
import org.mech.rougue.core.r.object.GIdFactory;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.geometry.Position;

public class Player implements MapObject {

	private GId gId;
	private RenderId renderId;

	private String name;
	private Position position;

	@Inject
	private PlayerSight sight;
	
	@Inject
	private GameContext gContext;

	public Player() {
		renderId = new RenderId(getType());
		gId = GIdFactory.next();
	}
	
	@PostConstruct
	public void setup() {
		gContext.add(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public PlayerSight getSight() {
		return sight;
	}

	public void setSight(PlayerSight sight) {
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

}
