package org.mech.rougue.core.r.model.inv;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Inventory implements Serializable{
	private static final long serialVersionUID = -5520478248994404893L;

	private final static Logger LOG = LoggerFactory.getLogger(Inventory.class);

	private final List<Item> items = new ArrayList<Item>();

	private float weight = .0F;

	public void take(final Item item) {
		items.add(item);
		recountWeight();
		LOG.info("item added to inventory [" + item.getName() + "]");
	}

	public void drop(final Item item) {
		items.remove(item);
		recountWeight();
		LOG.info("item droped from inventory [" + item.getName() + "]");
	}
	
	public Item getItem(final int index) {
		return index >= items.size() ? null : items.get(index);
	}

	public List<Item> getItems() {
		return items;
	}

	public float getTotalWeight() {
		return weight;
	}

	private void recountWeight() {
		weight = 0;
		for (final Item i : getItems()) {
			weight += i.getWeight();
		}
	}

}
