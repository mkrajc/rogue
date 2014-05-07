package org.mech.rougue.core.r.handler.register;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class SingleOnBulkRegistrationTest {

	@Test
	public void testIntegration() {
		String TEST_ITEM = "test";
		List<String> test = new ArrayList<String>();
		Registration registration = new SingleOnBulkRegistration<String>(test, TEST_ITEM);
		assertEquals(0, test.size());
		
		registration.register();
		assertEquals(1, test.size());
		
		registration.register();
		assertTrue(test.contains(TEST_ITEM));
		assertEquals(1, test.size());
		
		registration.unregister();
		assertEquals(0, test.size());
		
		registration.unregister();
		assertEquals(0, test.size());
		
		registration.register();
		assertTrue(test.contains(TEST_ITEM));
		assertEquals(1, test.size());
		
		registration.destroy();
		assertEquals(0, test.size());
		
		registration.register();
		assertEquals(0, test.size());
		
	}

	

}
