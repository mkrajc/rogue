package org.mech.rougue.core.r.model.inv;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mech.rougue.core.r.model.inv.item.AbstractEquipableItem;

public class EquipmentTest {
	
	private Equipment equipment;
	
	@Before
	public void setup(){
		equipment= new Equipment();
	}

	@Test
	public void testCanEquip() {
		final HeadEq head = new HeadEq();
		assertTrue(equipment.canEquip(head));
	}
	
	@Test
	public void testCanEquip2OnHead() {
		final HeadEq head = new HeadEq();
		equipment.equip(head, null);
		assertFalse(equipment.canEquip(head));
	}
	
	@Test
	public void testCanEquipFalse() {
		final NoneEq head = new NoneEq();
		assertFalse(equipment.canEquip(head));
	}
	
	@Test
	public void testCanEquipUnbelievable() {
		final UnbelievableSlotsEq head = new UnbelievableSlotsEq();
		assertFalse(equipment.canEquip(head));
	}
	
	
	static class NoneEq extends AbstractEquipableItem {
		@Override
		public EquipmentType getEquipmentType() {
			return EquipmentType.NONE;
		}
	}
	
	static class HeadEq extends AbstractEquipableItem {
		@Override
		public EquipmentType getEquipmentType() {
			return EquipmentType.HEAD;
		}
	}
	
	static class UnbelievableSlotsEq extends AbstractEquipableItem {
		@Override
		public EquipmentType getEquipmentType() {
			return EquipmentType.HAND;
		}
		
		@Override
		public int slots() {
			return Integer.MAX_VALUE;
		}
		
		
	}

}
