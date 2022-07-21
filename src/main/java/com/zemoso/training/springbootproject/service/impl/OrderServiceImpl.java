package com.zemoso.training.springbootproject.service.impl;

import com.zemoso.training.springbootproject.dao.OrderRepository;
import com.zemoso.training.springbootproject.entity.Order;
import com.zemoso.training.springbootproject.exceptions.ObjectNotFoundException;
import com.zemoso.training.springbootproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order findOrder(int userId, int itemId) {
        return orderRepository.findOrder(userId,itemId);
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> getUserOrders(int userId) {
        return orderRepository.getUserOrders(userId);
    }

    @Override
    public Order findById(int orderId) {
        Optional<Order> result = orderRepository.findById(orderId);

        Order order =null;

        if(result.isPresent()){
            order = result.get();
        }else{
            throw new ObjectNotFoundException("Did not find Object for id - "+orderId);
        }
        return order;
    }

    @Override
    public void deleteById(int orderId) {
        orderRepository.deleteById(orderId);
    }
}
