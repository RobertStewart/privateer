package com.wombatnation.privateer;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Privateer is a utility class for unit testing. Privateer allows you to access
 * all fields on an object, regardless of whether their accessibility is set to
 * public, package, protected or private. Privateer also allows you to invoke
 * any method on an object, regardless of accessibility.
 * <p>
 * For convenience in writing unit tests, security is automatically disabled when a
 * new Privateer object is constructed.
 * 
 * @author rstewart
 */
public class Privateer {
  private SecurityManager secman = System.getSecurityManager();
  private SecurityManager insecman = new InsecurityManager();
  
  /**
   * Constructs a Privateer object that allows you to access normally inaccessible
   * fields and methods. It does this by installing a SecurityManager that is completely unsecure.
   */
  public Privateer() {
    secureSystem(false);
  }
  
  /**
   * Sets whether system is secure, i.e., whether it uses the default SecurityManager or
   * unsecure SecurityManager.
   * 
   * @param secure Set to true to secure the system and to false to unsecure the system
   */
  public void secureSystem(boolean secure) {
    if (secure)
      System.setSecurityManager(secman);
    else
      System.setSecurityManager(insecman);
  }
  
  /**
   * Returns all declared Fields on Object o as accessible.
   * 
   * @param o Object to access
   * @return all declared Fields on Object o as accessible
   */
  public Field[] getAllFields(Object o) {
    Field[] fields = o.getClass().getDeclaredFields();
    AccessibleObject.setAccessible(fields, true);
    return fields;
  }
  
  /**
   * Returns all declared Methods on Object o as accessible.
   * 
   * @param o Object to access
   * @return all declared Methods on Object o as accessible
   */
  public Method[] getAllMethods(Object o) {
    Method[] methods = o.getClass().getDeclaredMethods();
    AccessibleObject.setAccessible(methods, true);
    return methods;
  }
  
  /**
   * Gets the specified field on Object o, even if that field is not normally accessible.
   * 
   * @param o Object to access
   * @param fieldName Name of field whose value will be returned
   * @throws NoSuchFieldException
   * @throws IllegalAccessException
   */
  public Object getField(Object o, String fieldName)
      throws NoSuchFieldException, IllegalAccessException {
    
    Field field = o.getClass().getDeclaredField(fieldName);
    field.setAccessible(true);
    return field.get(o);
  }
  
  /**
   * Sets the specified field on Object o to the specified value, even if that field is not normally accessible.
   * 
   * @param o Object to access
   * @param fieldName Name of field whose value will be set
   * @param value Object value that will be set for the field
   * @throws NoSuchFieldException
   * @throws IllegalAccessException
   */
  public void setField(Object o, String fieldName, Object value)
      throws NoSuchFieldException, IllegalAccessException {
    
    Field field = o.getClass().getDeclaredField(fieldName);
    field.setAccessible(true);
    field.set(o, value);
  }
  
  /**
   * Calls the specified method on the Object o with the specified arguments. Returns the
   * result as an Object.
   * <p>
   * The length of the vararg list of arguments to be passed to the method must match
   * what the method expects. If no arguments are expected, null can be passed. If one
   * argument is expected, that argument can be passed as an object. If multiple
   * arguments are expected, they can be passed as an Object array or as a comma-separated
   * list.
   * 
   * @param o Object to access
   * @param methodName Name of method to call
   * @param args Vararg list of arguments to pass to method
   * @return Object that is the result of calling the named method
   * @throws NoSuchMethodException
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   */
  public Object callMethod(Object o, String methodName, Object... args)
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

    Method method = null;
    if (args == null || args.length == 0) {
      method = o.getClass().getDeclaredMethod(methodName);
    } else {
      Class<?>[] types = new Class[args.length];
      for (int i = 0; i < args.length; i++) {
        types[i] = args[i].getClass();
      }
      method = o.getClass().getDeclaredMethod(methodName, types);
    }
    method.setAccessible(true);
    
    return method.invoke(o, args);
  }

}
