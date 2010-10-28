Privateer
================
http://github.com/RobertStewart/privateer

## Description
Privateer is a utility class for unit testing. Privateer allows you to access
all fields on an object, regardless of whether their accessibility is set to
public, package, protected or private. Privateer also allows you to invoke
any method on an object, regardless of accessibility.

For convenience in writing unit tests, security is automatically disabled when a
new Privateer object is constructed.

Of course, you can use Privateer outside of unit tests, but that might lead to [cats
and dogs living together or a robot uprising](http://steve-yegge.blogspot.com/2010/07/wikileaks-to-leak-5000-open-source-java.html).

## Usage
Privateer is very simple to use. In your code, first construct a Privateer object.

    Privateer p = new Privateer();
	
Then, you can use this object to access any field or method on another object.
For example, changing the value of an allegedly immutable Integer object:

    Integer i = Integer.valueOf(1);
    System.out.println("Immutable Integer i = " + i);
    Privateer p = new Privateer();
    try {
      p.setField(i, "value", 2);
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    System.out.println("Immutable Integer i = " + i);

produces the output:

    Immutable Integer i = 1
    Immutable Integer i = 2
	
The getAllFields() and getAllMethods() methods are convenient for quickly finding out what
fields and methods are available on an object.

## More Examples
Setting and getting private fields:

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

Calling private methods:

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

If a method takes no arguments, you can pass either null or an empty Object array as the third argument to callMethod().

If the method you want to call takes multiple arguments, add them as comma-separated items in the initializer, e.g.:

    Object[] args = new Object[]{o1, o2, o3};

The private field and methods in the NotSoPrivate class used in the unit tests for Privateer were declared as:

    private Object privateField;
    
    private Object getPrivateField() {
      return privateField;
    }
    
    private void setPrivateField(Object o) {
      this.privateField = o;
    }

## Credits
I developed this code at [Voxify](http://voxify.com). Thanks to Voxify for allowing me to release the
code under the open source Apache license.
