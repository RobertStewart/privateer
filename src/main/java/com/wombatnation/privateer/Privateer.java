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
   * Only fields declared on the class for Object o can be accessed.
   * 
   * @param o Object to access
   * @param fieldName Name of field whose value will be returned
   * @throws NoSuchFieldException if no field matches <code>fieldName</code>
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
   * Only fields declared on the class for Object o can be accessed.
   * 
   * @param o Object to access
   * @param fieldName Name of field whose value will be set
   * @param value Object value that will be set for the field
   * @throws NoSuchFieldException if no field matches <code>fieldName</code>
   * @throws IllegalAccessException
   * @throws IllegalArgumentException if the type of <code>value</code> is incorrect
   */
  public void setField(Object o, String fieldName, Object value)
      throws NoSuchFieldException, IllegalAccessException {
    
    Field field = o.getClass().getDeclaredField(fieldName);
    field.setAccessible(true);
    field.set(o, value);
  }
  
  /**
   * Calls the specified method on the Object o with the specified arguments. Returns the
   * result as an Object. Only methods declared on the class for Object o can be called.
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
   * @throws NoSuchMethodException if the name or arguments don't match any declared method
   * @throws IllegalAccessException
   * @throws InvocationTargetException if some unexpected error occurs when invoking a method that was thought to match
   * @throws IllegalArgumentException if the argument count doesn't match any method that has the same name
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
      try {
        method = o.getClass().getDeclaredMethod(methodName, types);
      } catch (NoSuchMethodException e) {
        // Try more complex comparison for matching args to supertypes in the params
        method = findSupertypeMethod(o, methodName, types);
      }
    }
    
    if (method == null) {
      throw new NoSuchMethodException("Found no method named" + methodName + " with matching params");
    }
    
    method.setAccessible(true);
    
    return method.invoke(o, args);
  }
  
  /**
   * Examines each argument to see if a param in the same position is a supertype. This method is
   * needed because getDeclaredMethod() does a formal type match. For example, if the method takes
   * a java.util.List as a parameter, getDeclaredMethod() will never match, since the formal type
   * of whatever you pass will be a concrete class.
   * 
   * @param o
   * @param methodName
   * @param types
   * @return A matching Method or null
   */
  private Method findSupertypeMethod(Object o, String methodName, Class<?>[] types) {
    
    Method matchingMethod = null;
    Method[] methods = o.getClass().getDeclaredMethods();
    
    methodloop:
    for (Method method : methods) {
      if (methodName.equals(method.getName())) {
        Class<?>[] params = method.getParameterTypes();
        if (params.length == types.length) {
          for (int i = 0; i < params.length; i++) {
            // Check if param is supertype of arg in the same position
            if (!params[i].isAssignableFrom(types[i])) {
              break methodloop;
            }
          }
        }
        // If we reach here, then all params and args were compatible
        matchingMethod = method;
        break;
      }
    }
    
    return matchingMethod;
  }

}
