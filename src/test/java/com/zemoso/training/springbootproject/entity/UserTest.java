package com.zemoso.training.springbootproject.entity;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;

class UserTest {


    @Mock
    Authority authority;

    @Mock
    User user;

    @Test
    void userTest(){
        user = new User(1,"Akash","password",1,authority);
        assertEquals(1,user.getId());
        assertEquals("Akash",user.getUserName());
        assertEquals("password",user.getPassword());
        assertEquals(1,user.getEnabled());
        assertEquals(authority,user.getAuthority());

    }

    @Test
    void setUserId(){
        user = new User(1,"Akash","password",1,authority);
        user.setId(2);
        assertEquals(2,user.getId());
    }

    @Test
    void setUserName(){
        user = new User(1,"Akash","password",1,authority);
        user.setUserName("User");
        assertEquals("User",user.getUserName());
    }

    @Test
    void setUserpassword(){
        user = new User(1,"Akash","password",1,authority);
        user.setPassword("pass");
        assertEquals("pass",user.getPassword());
    }

    @Test
    void setUserEnabled(){
        user = new User(1,"Akash","password",1,authority);
        user.setEnabled(0);
        assertEquals(0,user.getEnabled());

    }

    @Test
    void setUserAuthority(){
        user = new User(1,"Akash","password",1,authority);
        authority = new Authority();
        user.setAuthority(authority);
        assertEquals(authority,user.getAuthority());
    }
}
