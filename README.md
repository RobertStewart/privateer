Privateer
================
[Project site](http://wombanation.com/privateer)

[Source code on GitHub](http://github.com/RobertStewart/privateer)

# Description
Privateer allows you to take advantage of the field and method access control features of Java
in your public API, while easily unit testing features that would normally be difficult or impossible to test.

Privateer gives you easy access to all fields on an object, regardless of whether their accessibility is set
to public, package, protected or private. Privateer also allows you to invoke any method on an object,
regardless of accessibility.

Of course, you can use Privateer outside of unit tests, but that might lead to [cats and dogs living
together or a robot uprising](http://steve-yegge.blogspot.com/2010/07/wikileaks-to-leak-5000-open-source-java.html).

# Why Use Privateer?
Through access modifiers such as public, protected, private, etc., and a security manager, the Java
language provides developers the ability to encapsulate data and logic in a class. In addition to
simplifying the public API, these features of Java give the developer the ability to modify the
implementation of hidden fields and methods without worrying about breaking code that uses the public API.

However, when that same developer is writing unit tests, this feature becomes a hindrance. Often
a private method will lend itself to simple unit testing if it can be called directly, but will be much
more difficult to test if it can be reached only through a public or protected method. Rather than
making that method more accessible to everyone just so it can be tested, it would be great if the
method could be left as private, but still be callable from a unit test.

Also, it is sometimes very difficult to create the desired initial state for a unit test just through
public methods. For example, you may wish to cause a private Date field to have a specific date
in the past, but the only public methods cause the date to always be set to now. Rather than adding
a public method to set the Date field, it would be great if the field remained hidden from the API,
but were settable from a unit test.

# Usage
Privateer is very simple to use. In your code, first construct a Privateer object.

    Privateer p = new Privateer();

For convenience in writing unit tests, security is automatically disabled when a new Privateer object
is constructed.
    
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

# Credits
I developed this code at [Voxify](http://voxify.com). Thanks to Voxify for allowing me to release the
code under the open source Apache license.
