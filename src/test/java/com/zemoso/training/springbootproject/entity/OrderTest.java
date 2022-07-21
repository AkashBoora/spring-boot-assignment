package com.zemoso.training.springbootproject.entity;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.expression.common.LiteralExpression;

import static org.junit.Assert.*;


class OrderTest {

    @Mock
    User user;

    @Mock
    Item item;

    @Mock
    Order order;

    @Test
    void orderTest(){
        order = new Order(user,item,5);
        order.setId(5);
        assertEquals(5,order.getId());
        assertEquals(user,order.getUser());
        assertEquals(item,order.getItem());
        assertEquals(5,order.getQuantity());
    }

    @Test
    void setUserTest(){
        order = new Order(user,item,5);
        item = new Item();
        order.setUser(user);
        assertEquals(user,order.getUser());
    }

    @Test
    void setItemTest(){
        order = new Order(user,item,5);
        user = new User();
        order.setItem(item);
        assertEquals(item,order.getItem());
    }

    @Test
    void setQuantityTest(){
        order = new Order(user,item,5);
        order.setQuantity(3);
        assertEquals(3,order.getQuantity());
    }

    @Test
    void increaseQuantity(){
        order = new Order(user,item,5);
        order.increaseQuantity();
        assertEquals(6,order.getQuantity());
    }
}
