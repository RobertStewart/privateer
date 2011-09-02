package com.wombatnation.privateer;

import org.junit.Test;

public class BadMethodTest {

  private void name() {
  }
  
  private void twoArgs(Integer a, String b) {
  }
  
  @Test (expected=NoSuchMethodException.class)
  public void testNameMismatch() throws Exception {
    Privateer p = new Privateer();
    p.callMethod(this, "nameMismatch");
  }
  
  @Test (expected=NoSuchMethodException.class)
  public void testArgTypeMismatch() throws Exception {
    Privateer p = new Privateer();
    p.callMethod(this, "twoArgs", "arg1", Integer.valueOf(2));
  }
  
  @Test (expected=IllegalArgumentException.class)
  public void testArgCountMismatch() throws Exception {
    Privateer p = new Privateer();
    p.callMethod(this, "twoArgs", Integer.valueOf(4));
  }
  
  
}
