package com.codenotfound.model;

import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "order", description = "An order")
public class Order {

  @ApiModelProperty(name = "id", required = true,
      value = "The order id")
  private String id;

  @ApiModelProperty(name = "customer", required = true,
      value = "The name of the customer")
  private String customer;

  @ApiModelProperty(name = "items", required = true,
      value = "The items of the order")
  private List<Item> items;

  public Order() {}

  public Order(String id, String customer, List<Item> items) {

    this.id = id;
    this.customer = customer;
    this.items = items;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }
}
