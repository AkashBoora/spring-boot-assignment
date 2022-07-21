package com.zemoso.training.springbootproject.service;

import com.zemoso.training.springbootproject.entity.Order;

import java.util.List;

public interface OrderService {
    public Order findOrder(int userId, int itemId);

    void saveOrder(Order order);

    public List<Order> getUserOrders(int userId);

    public Order findById(int orderId);

    public void deleteById(int orderId);
}
