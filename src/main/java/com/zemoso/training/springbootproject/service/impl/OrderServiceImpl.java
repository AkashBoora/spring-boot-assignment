package com.zemoso.training.springbootproject.service.impl;

import com.zemoso.training.springbootproject.dao.OrderRepository;
import com.zemoso.training.springbootproject.entity.Item;
import com.zemoso.training.springbootproject.entity.Order;
import com.zemoso.training.springbootproject.entity.User;
import com.zemoso.training.springbootproject.exceptions.ObjectNotFoundException;
import com.zemoso.training.springbootproject.service.ItemService;
import com.zemoso.training.springbootproject.service.OrderService;
import com.zemoso.training.springbootproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    UserService userService;

    @Autowired
    ItemService itemService;

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

    @Override
    public void addItemToUser(String userName, int itemId) {
        User user = userService.findUserByName(userName);
        Item item = itemService.findById(itemId);
        Order order;
        Order result= findOrder(user.getId(),item.getId());
        if(result == null){
            order = new Order(user,item,1);
        }else{
            order = result;
            order.increaseQuantity();
        }
        item.setQuantity(item.getQuantity()-1);
        itemService.saveItem(item);
        saveOrder(order);
    }

    @Override
    public List<Order> userOrdersList(String userName) {
        User user = userService.findUserByName(userName);
        return getUserOrders(user.getId());
    }

    @Override
    public List<Order> getUserOrders(int userId) {
        return orderRepository.getUserOrders(userId);
    }

    @Override
    public void deleteOrder(int orderId) {
        Order order = findById(orderId);
        Item item = order.getItem();
        item.setQuantity(order.getQuantity()+item.getQuantity());
        itemService.saveItem(item);
        deleteById(orderId);
    }

}
