## Privateer ##
http://github.com/RobertStewart/log4mongo-java

## Description
Privateer is a utility class for unit testing. Privateer allows you to access
all fields on an object, regardless of whether their accessibility is set to
public, package, protected or private. Privateer also allows you to invoke
any method on an object, regardless of accessibility.

For convenience in writing unit tests, security is automatically disabled when a
new Privateer object is constructed.

## Usage
Privateer is very simple to use. In your unit test, first construct a Privateer object.

	Privateer p = new Privateer();
	
Then, you can use this object to access any field or method on another object. For example, to change the value of an allegedly immutable Integer object.

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

Produces the output:

	Immutable Integer i = 1
	Immutable Integer i = 2
	
The getAllFields and getAllMethods methods are convenient for quickly finding out what fields and methods are available on an object.

## Example
The following code example is from the unit test for Privateer.

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

If a method takes no arguments, you can pass either null or an empty Object array as the third argument to callMethod().

The private field and methods in the NotSoPrivate class in the unit test for Privateer are declared as:

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
