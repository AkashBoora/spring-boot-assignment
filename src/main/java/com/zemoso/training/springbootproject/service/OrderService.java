package com.zemoso.training.springbootproject.service;

import com.zemoso.training.springbootproject.entity.Order;

import java.util.List;

public interface OrderService {
    public Order findOrder(int userId, int itemId);

    public void saveOrder(Order order);

    public Order findById(int orderId);

    public void deleteById(int orderId);

    public void addItemToUser(String userName, int itemId);

    public List<Order> userOrdersList(String userName);

    public List<Order> getUserOrders(int userId);

    public void deleteOrder(int orderId);

}
