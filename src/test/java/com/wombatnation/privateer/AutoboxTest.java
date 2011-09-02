package com.wombatnation.privateer;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AutoboxTest {

  private Integer intObj = null;
  
  private int oneInteger(Integer a) {
    return a;
  }
  
  @Test
  public void testIntegerSetField() throws Exception {
    Privateer p = new Privateer();
    int i = 1;
    p.setField(this, "intObj", 1);
    Object o = p.getField(this, "intObj");
    Integer integer = (Integer) o;
    assertEquals(Integer.valueOf(i), integer);
  }
  
  @Test
  public void testIntMethod() throws Exception {
    Privateer p = new Privateer();
    int i = 1;
    // i should get autoboxed to an Integer when Object array is constructed
    Object result = p.callMethod(this, "oneInteger", i);
    Integer intResult = (Integer) result;
    assertEquals(Integer.valueOf(i), intResult);
  }
}
