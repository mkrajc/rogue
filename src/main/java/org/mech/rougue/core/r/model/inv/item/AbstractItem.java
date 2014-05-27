package org.mech.rougue.core.r.model.inv.item;

import org.mech.rougue.core.r.model.inv.Item;
import org.mech.rougue.core.r.model.inv.ItemType;
import org.mech.rougue.core.r.object.GId;
import org.mech.rougue.core.r.object.GIdFactory;

public class AbstractItem implements Item {

	protected GId id;
	protected String name = "UNDEF";
	protected ItemType type;
	protected float weight = 0.F;

	public AbstractItem() {
		this.id = GIdFactory.next();
	}

	@Override
	public GId id() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ItemType getType() {
		return type;
	}

	@Override
	public float getWeight() {
		return weight;
	}

	public void setId(final GId id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setType(final ItemType type) {
		this.type = type;
	}

	public void setWeight(final float weight) {
		this.weight = weight;
	}
	
	@Override
	public String getRenderType() {
		return type.name().toLowerCase();
	}

}
