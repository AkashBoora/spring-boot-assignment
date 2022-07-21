package com.zemoso.training.springbootproject.dto;


import com.zemoso.training.springbootproject.entity.Authority;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;


class UserDtoTest {

    @Mock
    UserDto userDto;

    @MockBean
    Authority authority;


    @Test
    void userDtoTest(){
        userDto =new UserDto(2,"Akash","$2a$10$Hw3JYaRlLyAqRn0HlxsxaOMP8kmH0Eu/aavUm4Mp8oOdAp33H7L0a",1,authority);
        assertEquals(2,userDto.getId());
        assertEquals("Akash",userDto.getUserName());
        assertEquals("$2a$10$Hw3JYaRlLyAqRn0HlxsxaOMP8kmH0Eu/aavUm4Mp8oOdAp33H7L0a",userDto.getPassword());
        assertEquals(1,userDto.getEnabled());
        assertEquals(authority,userDto.getAuthority());
    }

    @Test
    void setUserIdTest(){
        userDto =new UserDto(2,"Akash","$2a$10$Hw3JYaRlLyAqRn0HlxsxaOMP8kmH0Eu/aavUm4Mp8oOdAp33H7L0a",1,authority);
        userDto.setId(3);
        assertEquals(3,userDto.getId());
    }

    @Test
    void setUserNameTest(){
        userDto =new UserDto(2,"Akash","$2a$10$Hw3JYaRlLyAqRn0HlxsxaOMP8kmH0Eu/aavUm4Mp8oOdAp33H7L0a",1,authority);
        userDto.setUserName("Vikram");
        assertEquals("Vikram",userDto.getUserName());
    }

    @Test
    void setUserPasswordTest(){
        userDto =new UserDto(2,"Akash","$2a$10$Hw3JYaRlLyAqRn0HlxsxaOMP8kmH0Eu/aavUm4Mp8oOdAp33H7L0a",1,authority);
        userDto.setPassword("2a$10$Hw3JYaRlLyAqRn0HlxsxaOMP8kmH0Eu/aavUm4Mp8oOdAp33H7L798");
        assertEquals("2a$10$Hw3JYaRlLyAqRn0HlxsxaOMP8kmH0Eu/aavUm4Mp8oOdAp33H7L798",userDto.getPassword());
    }

    @Test
    void noArgConstructor(){
        userDto = new UserDto();
        assertEquals(null,userDto.getUserName());
    }

}
