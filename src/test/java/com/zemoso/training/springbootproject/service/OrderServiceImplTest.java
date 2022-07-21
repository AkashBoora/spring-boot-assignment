package com.zemoso.training.springbootproject.service;


import com.zemoso.training.springbootproject.dao.OrderRepository;
import com.zemoso.training.springbootproject.entity.Item;
import com.zemoso.training.springbootproject.entity.Order;
import com.zemoso.training.springbootproject.entity.User;
import com.zemoso.training.springbootproject.exceptions.ObjectNotFoundException;
import com.zemoso.training.springbootproject.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class OrderServiceImplTest {

    @Mock
    Order order;

    @Mock
    Item item;

    @Mock
    User user;

    @MockBean
    OrderRepository orderRepository;

    @Autowired
    OrderServiceImpl orderServiceImpl;


    @Test
    void findById(){
        order = new Order(user,item,3);
        doReturn(Optional.of(order))
                .when(orderRepository)
                .findById(0);
        assertEquals(order,orderServiceImpl.findById(0));
    }

    @Test
    void deleteById(){
        orderServiceImpl.deleteById(2);
        verify(orderRepository,times(1)).deleteById(2);
    }

    @Test
    void saveOrder(){
        orderServiceImpl.saveOrder(order);
        verify(orderRepository,times(1)).save(order);
    }

    @Test
    void findOrder(){
        order = new Order(user,item,3);
        doReturn(order)
                .when(orderRepository)
                .findOrder(2,3);
        assertEquals(order,orderServiceImpl.findOrder(2,3));
    }


    @Test
    void userOders(){
        order = new Order(user,item,3);
        doReturn(Stream.of(item).collect(Collectors.toList()))
                .when(orderRepository)
                .getUserOrders(3);
        assertEquals(1,orderServiceImpl.getUserOrders(3).size());

    }

    @Test
    void findByIdException(){
        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            orderServiceImpl.findById(100);
        });

        String expectedMessage = "Did not find Object for id - 100";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


}
