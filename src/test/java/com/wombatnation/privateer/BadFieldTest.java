package com.wombatnation.privateer;

import org.junit.Test;

public class BadFieldTest {

    private String name = null;
    
    @Test (expected=NoSuchFieldException.class)
    public void testNameMismatch() throws Exception {
      Privateer p = new Privateer();
      p.getField(this, "notMyName");
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void testBadArgSet() throws Exception {
      Privateer p = new Privateer();
      p.setField(this, "name", Integer.valueOf(1));
    }
}
