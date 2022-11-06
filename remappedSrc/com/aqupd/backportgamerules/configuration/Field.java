package com.aqupd.backportgamerules.configuration;

public class Field<T> {

  private T value;

  public Field(final T value) {
    this.value = value;
  }

  public T getValue() {
    return value;
  }

  public void setValue(final T value) {
    this.value = value;
  }
}
