package com.wombatnation.privateer;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class ImmutableTest {

  @Test
  public void testMutateImmutableInteger() {
    int origInt = 1;
    int modInt = 2;
    
    Integer i = Integer.valueOf(origInt);
    assertTrue(origInt == i.intValue());
    
    Privateer p = new Privateer();
    
    try {
      p.setField(i, "value", modInt);
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
      fail();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      fail();
    }

    assertTrue(modInt == i.intValue());
  }
}
