package com.akukhtin.ishop.service;

import com.akukhtin.ishop.model.Item;
import com.akukhtin.ishop.model.Order;

import java.util.List;

public interface OrderService {

    Order completeOrder(List<Item> items, Long userId);

    Order create(Order order);

    Order get(Long id);

    List<Order> getAll();

    List<Item> getAllItems(Long orderId);

    Order update(Order order);

    Order delete(Long id);

    Order deleteByOrder(Order order);
}
