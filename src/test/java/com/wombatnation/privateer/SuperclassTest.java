package com.wombatnation.privateer;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class SuperclassTest {
  
  
  private int arrayListMethod(ArrayList<Integer> l) {
    return l.size();
  }
  
  private int listMethod(List<Integer> l) {
    return l.size();
  }
  
  private int twoListMethod(List<Integer> l1, List<Integer> l2) {
    return l1.size() + l2.size();
  }
  
  private int mixedListMethod(ArrayList<Integer> l1, List<Integer> l2) {
    return l1.size() + l2.size();
  }

  /**
   * Asserts that direct match of param types works.
   * @throws Exception
   */
  @Test
  public void testArrayListMethod() throws Exception {
    Privateer p = new Privateer();
    
    List<Integer> l = new ArrayList<Integer>();
    l.add(1);
    l.add(2);

    Object result = null;
    Integer i = null;
    
    result = p.callMethod(this, "arrayListMethod", l);
    i = (Integer) result;
    assertEquals(l.size(), i.intValue());
  }
  
  /**
   * Asserts that any param can be a supertype of the argument in the same position.
   * @throws Exception
   */
  @Test
  public void testListMethod() throws Exception {
    Privateer p = new Privateer();
    
    List<Integer> l = new ArrayList<Integer>();
    l.add(1);
    l.add(2);
    List<Integer> ll = new LinkedList<Integer>();
    ll.add(1);
    ll.add(2);

    Object result = null;
    Integer i = null;
    
    result = p.callMethod(this, "listMethod", l);
    i = (Integer) result;
    assertEquals("ArrayList failure", l.size(), i.intValue());
    
    result = p.callMethod(this, "listMethod", ll);
    i = (Integer) result;
    assertEquals("LinkedList failure", ll.size(), i.intValue());
    
    result = p.callMethod(this, "twoListMethod", l, ll);
    i = (Integer) result;
    assertEquals("LinkedList failure", l.size() + ll.size(), i.intValue());

    result = p.callMethod(this, "mixedListMethod", l, ll);
    i = (Integer) result;
    assertEquals("Mixed list failure", l.size() + ll.size(), i.intValue());
  }
}
