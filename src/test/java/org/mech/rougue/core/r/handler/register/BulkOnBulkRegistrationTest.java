package org.mech.rougue.core.r.handler.register;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class BulkOnBulkRegistrationTest {

	@Test
	public void testIntegrationDifferentNumbers() {
		List<Integer> testList = new ArrayList<Integer>();
		testList.add(1);
		testList.add(2);

		List<Integer> partialList = new ArrayList<Integer>();
		partialList.add(3);
		partialList.add(4);

		final BulkOnBulkRegistration<Integer> registration = new BulkOnBulkRegistration<Integer>(testList, partialList);
		assertEquals(2, testList.size());
		
		registration.register();
		assertEquals(4, testList.size());
		
		registration.register();
		assertEquals(4, testList.size());
		
		registration.unregister();
		assertEquals(2, testList.size());
		
		registration.unregister();
		assertEquals(2, testList.size());
		
		registration.register();
		assertEquals(4, testList.size());
		
		registration.destroy();
		assertEquals(2, testList.size());
		
		//after destroy
		registration.register();
		assertEquals(2, testList.size());
		
		registration.register();
		assertEquals(2, testList.size());

	}
	
	@Test
	public void testIntegrationPartial() {
		List<Integer> testList = new ArrayList<Integer>();
		testList.add(1);
		testList.add(2);

		List<Integer> partialList = new ArrayList<Integer>();
		partialList.add(3);
		partialList.add(4);

		final BulkOnBulkRegistration<Integer> registration = new BulkOnBulkRegistration<Integer>(testList, partialList);
		assertEquals(2, testList.size());
		
		registration.register();
		assertEquals(4, testList.size());
		
		registration.unregister();
		assertEquals(2, testList.size());

		Registration par3 = registration.get(3);
		Registration par4 = registration.get(4);
		
		par3.register();
		assertEquals(3, testList.size());
		
		registration.unregister();
		assertEquals(2, testList.size());
		
		par4.register();
		assertEquals(3, testList.size());
		
		registration.register();
		assertEquals(4, testList.size());
		
		registration.destroy();
		
		//after destroy
		registration.register();
		assertEquals(2, testList.size());
		registration.unregister();
		assertEquals(2, testList.size());

	}

}
