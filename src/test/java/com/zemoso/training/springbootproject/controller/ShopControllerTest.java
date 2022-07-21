package com.zemoso.training.springbootproject.controller;


import com.zemoso.training.springbootproject.dto.ItemDto;
import com.zemoso.training.springbootproject.dto.UserDto;
import com.zemoso.training.springbootproject.entity.Authority;
import com.zemoso.training.springbootproject.entity.Item;
import com.zemoso.training.springbootproject.entity.Order;
import com.zemoso.training.springbootproject.entity.User;
import com.zemoso.training.springbootproject.service.ItemService;
import com.zemoso.training.springbootproject.service.OrderService;
import com.zemoso.training.springbootproject.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
class ShopControllerTest {

    @Mock
    Item item;

    @Mock
    ItemDto itemDto;

    @MockBean
    User user;

    @Mock
    List<Item> items;

    @Mock
    Order order;

    private MockMvc mockMvc;
    @MockBean
    private ItemService itemService;
    @MockBean
    private UserService userService;
    @MockBean
    private OrderService orderService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Test
    void homePage() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(get("/shop/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    void afterLogin() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(get("/shop/loginHome"))
                .andExpect(status().isOk())
                .andExpect(view().name("after-login"));
    }

    @Test
    void itemsList() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(get("/shop/itemsList"))
                .andExpect(status().isOk())
                .andExpect(view().name("items-list"));
    }

    @Test
    void usersList() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/shop/usersList"))
                .andExpect(status().isOk())
                .andExpect(view().name("users-list"));
    }

    @Test
    void showFormForAddItem() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/shop/showFormForAddItem"))
                .andExpect(status().isOk())
                .andExpect(view().name("item-form"));
    }


    @Test
    void saveItem() throws Exception{
        itemDto = new ItemDto(5,"T Shirt",599,56,"Branded");
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(post("/shop/saveItem").flashAttr("item",itemDto )).andExpect(status().is(302)).andExpect(view().name("redirect:/shop/itemsList"));
    }

    @Test
    void saveItemException() throws Exception{
        itemDto = new ItemDto(5,"T Shirt",599,56,"Branded");
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(post("/shop/saveItem").flashAttr("itemDto",itemDto )).andExpect(status().is(200)).andExpect(view().name("item-form"));
    }

    @Test
    void deleteItem() throws Exception {
        item = new Item(1,"Polo T Shirt",599,15,"Good qality t-shirt");
        Mockito.when(itemService.findById(1)).thenReturn(item);
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(get("/shop/deleteItem").queryParam("itemId","1")).andExpect(status().is(302))
                .andExpect(view().name("redirect:/shop/itemsList"));
    }

}
