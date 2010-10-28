package com.wombatnation.privateer;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
   * Assert that Privateer can access private fields that would normally
   * not be accessible.
   * 
   * @throws Exception
   */
  @Test
  public void testPrivateField() throws Exception {
    
    // Create Privateer object to allow access to normally inaccessible fields and methods
    Privateer p = new Privateer();
    
    Object o = new Object();
    NotSoPrivate nsp = new NotSoPrivate();
    // Set Object o into a private field on the nsp object
    p.setField(nsp, "privateField", o);
    // Get object back from private method on nsp
    Object result = p.getField(nsp, "privateField");
    // Assert that we got back the object we set in the private field
    assertTrue(o == result);
    
  }
  
  /**
   * Asserts that Privateer can access private methods that would normally
   * not be accessible.
   * 
   * @throws Exception
   */
  @Test
  public void testPrivateMethod() throws Exception {

    // Create Privateer object to allow access to normally inaccessible fields and methods
    Privateer p = new Privateer();
        
    Object o = new Object();
    NotSoPrivate nsp = new NotSoPrivate();
    // Call a private method on nsp to set value to new object
    p.callMethod(nsp, "setPrivateField", o);
    Object[] noArgs = new Object[]{};
    Object result = p.callMethod(nsp, "getPrivateField", noArgs);
    // Assert that we used private methods to change a private field and retrieve its value
    assertTrue(o == result);
    
    Object o2 = new Object();
    // Could just use o2 as arg, but this is how you would pass multiple args
    Object[] args = new Object[]{o2};
    p.callMethod(nsp, "setPrivateField", args);
    result = p.callMethod(nsp, "getPrivateField", noArgs);
    // Assert that we used private methods to change a field again and retrieve its value
    assertTrue(o2 == result);
    
  }

}
