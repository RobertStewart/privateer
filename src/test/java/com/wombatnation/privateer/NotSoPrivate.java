package com.wombatnation.privateer;

/**
 * Class for testing ability of Privateer to access private fields and methods.
 */
public class NotSoPrivate {
  private Object privateField;
  public Object publicField;

  @SuppressWarnings("unused")
  private Object getPrivateField() {
    return privateField;
  }

  @SuppressWarnings("unused")
  private void setPrivateField(Object o) {
    this.privateField = o;
  }

  public Object getPublicField() {
    return publicField;
  }

  public void setPublicField(Object o) {
    this.publicField = o;
  }
}
