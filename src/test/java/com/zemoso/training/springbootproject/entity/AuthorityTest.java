package com.zemoso.training.springbootproject.entity;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.junit.Assert.*;

class AuthorityTest {

    @Mock
    Authority authority;

    @Test
    void authorityTest(){
        authority =new Authority(1,"name","Role");
        assertEquals(1,authority.getId());
        assertEquals("name",authority.getUserName());
        assertEquals("Role",authority.getAuthorityType());
    }

    @Test
    void setIdTest(){
        authority =new Authority(1,"name","Role");
        authority.setId(5);
        assertEquals(5,authority.getId());
    }

    @Test
    void setNameTest(){
        authority =new Authority(1,"name","Role");
        authority.setUserName("Name");
        assertEquals("Name",authority.getUserName());
    }

    @Test
    void setTypeTest(){
        authority =new Authority(1,"name","Role");
        authority.setAuthorityType("role");
        assertEquals("role",authority.getAuthorityType());
    }

    @Test
    void twoArgConstructorTest(){
        authority =new Authority("name","Role");
        assertEquals("name",authority.getUserName());
        assertEquals("Role",authority.getAuthorityType());
    }
}
