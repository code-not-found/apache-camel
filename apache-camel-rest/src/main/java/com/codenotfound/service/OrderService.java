package com.codenotfound.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.codenotfound.model.Item;
import com.codenotfound.model.Order;

@Service
public class OrderService {

  private final Map<String, Order> orders = new HashMap<>();

  public OrderService() {
    List<Item> items = new ArrayList<>();
    items.add(new Item("wheel", 20));
    items.add(new Item("engine", 1000));

    orders.put("001", new Order("001", "John Doe", items));
  }

  public Order findById(String id) {
    return orders.get(id);
  }
}
