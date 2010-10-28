package com.wombatnation.privateer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.wombatnation.privateer.Privateer;

public class PrivateerTest {

  /**
   * Nothing special here. Just accessing public methods.
   */
  @Test
  public void testPublic() {
    Object o = new Object();
    NotSoPrivate nsp = new NotSoPrivate();
    nsp.setPublicField(o);
    assertTrue(o == nsp.getPublicField());
  }
  
  /**
   * Asserts that Privateer can access private fields and methods that
   * would normally not be accessible.
   * 
   * @throws Exception
   */
  @Test
  public void testPrivate() throws Exception {
    
    // Create the Privateer class that will allow access to normally inaccessible fields and methods
    Privateer p = new Privateer();
    
    Object o = new Object();
    NotSoPrivate nsp = new NotSoPrivate();
    // Set Object o into a private field on the nsp object
    p.setField(nsp, "privateField", o);
    // Call a private method on nsp to get the value back
    Object[] args = null;
    Object result = p.callMethod(nsp, "getPrivateField", args);
    // Assert that we got back the object we set in the private field
    assertTrue(o == result);
    
    Object o2 = new Object();
    args = new Object[] {o2};
    // Call a private method on nsp to set value to new object
    p.callMethod(nsp, "setPrivateField", o2);
    result = p.callMethod(nsp, "getPrivateField", new Object[]{});
    // Assert that we used a private method to change a private field
    assertFalse(o == result);
    assertTrue(o2 == result);
    
  }

}
