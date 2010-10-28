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
   * Installs a SecurityManager that is completely unsecure.
   */
  public Privateer() {
    secureSystem(false);
  }
  
  /**
   * Sets whether system is secure, i.e., uses the default SecurityManager or
   * uses an unsecure SecurityManager.
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
   * Returns all declared fields on Object o as accessible.
   * 
   * @param o
   * @return all declared fields on Object o as accessible
   */
  public Field[] getAllFields(Object o) {
    Field[] fields = o.getClass().getDeclaredFields();
    AccessibleObject.setAccessible(fields, true);
    return fields;
  }
  
  /**
   * Returns all declared methods on Object o as accessible.
   * 
   * @param o
   * @return all declared methods on Object o as accessible
   */
  public Method[] getAllMethods(Object o) {
    Method[] methods = o.getClass().getDeclaredMethods();
    AccessibleObject.setAccessible(methods, true);
    return methods;
  }
  
  /**
   * Sets the specified field on Object o to the specified value, even if that field is not normally accessible.
   * 
   * @param o
   * @param fieldName
   * @param value
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
   * 
   * @param o
   * @param methodName
   * @param args
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
