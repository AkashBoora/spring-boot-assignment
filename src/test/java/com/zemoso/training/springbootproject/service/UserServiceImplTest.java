package com.zemoso.training.springbootproject.service;

import com.zemoso.training.springbootproject.dao.UserRepository;
import com.zemoso.training.springbootproject.dto.ItemDto;
import com.zemoso.training.springbootproject.dto.UserDto;
import com.zemoso.training.springbootproject.entity.Authority;
import com.zemoso.training.springbootproject.entity.Item;
import com.zemoso.training.springbootproject.entity.User;
import com.zemoso.training.springbootproject.exceptions.ObjectNotFoundException;
import com.zemoso.training.springbootproject.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceImplTest {

    @Mock
    Authority authority;

    @MockBean
    User user;

    @Mock
    UserDto userDto;

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    private ModelMapper modelMapper;


    @Test
    void findById(){
        user = new User("Akash","test132");
        doReturn(Optional.of(user))
                .when(userRepository)
                .findById(2);
        assertEquals("Akash",userServiceImpl.findById(2).getUserName());
    }

    @Test
    void findAll(){
        User user1 = new User("Akash","test132");
        User user2 = new User("Akash","test132");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        doReturn(users)
                .when(userRepository)
                .findAll();
        doReturn(Optional.of(user1))
                .when(userRepository)
                .findById(1);
        assertEquals(1,userServiceImpl.findAll().size());
    }

    @Test
    void findByIdException(){
        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            userServiceImpl.findById(100);
        });

        String expectedMessage = "Did not find Object for id - 100";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void findByUserName(){
        userServiceImpl.findUserByName("Akash");
        verify(userRepository,times(1)).findUserByName("Akash");
    }

    @Test
    void saveUser(){
        user = new User("Akash","dfghj");
        userServiceImpl.saveUser(user);
        verify(userRepository,times(1)).save(user);

    }
}
