package com.codenotfound.service;

public class OrderNotFoundException extends Exception {

  private static final long serialVersionUID = 1L;

  public OrderNotFoundException(int id) {
    super("Order not found with ID=" + id);
  }
}
