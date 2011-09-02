package com.wombatnation.privateer;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

public class PrimitivesTest {
	
	private int intMethod(int a) {
		return a;
	}

//	@Test
	@Ignore
	public void testIntArg() throws Exception {
		Privateer p = new Privateer();
		
		int a = 1;
		Object result = p.callMethod(this, "intMethod", a);
		Integer i = (Integer) result;
		assertEquals(i.intValue(), a);
	}
}
