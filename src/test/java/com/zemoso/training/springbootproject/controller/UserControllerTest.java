package com.zemoso.training.springbootproject.controller;


import com.zemoso.training.springbootproject.dto.UserDto;
import com.zemoso.training.springbootproject.entity.Authority;
import com.zemoso.training.springbootproject.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    UserDto userDto;

    @Mock
    Authority authority;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    void accessDenied() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/user/showFormForAddUser"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-form"));
    }

    @Test
    void showFormForAddUser() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/user/accessDenied"))
                .andExpect(status().isOk())
                .andExpect(view().name("access-denied"));
    }


}
