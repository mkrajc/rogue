package org.mech.rougue.core.r.model.inv;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Inventory {

	private final static Logger LOG = LoggerFactory.getLogger(Inventory.class);

	private final List<Item> items = new ArrayList<Item>();
	
	public void take(final Item item){
		items.add(item);
		LOG.info("item added to inventory " + item);
	}
	
	public void drop(final Item item) {
		items.add(item);
		LOG.info("item droped to inventory " + item);
	}

	public List<Item> getItems() {
		return items;
	}

}
