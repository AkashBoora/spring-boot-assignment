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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {

    public static final String ITEM_FORM = "item-form";
    public static final String REDIRECT_SHOP_ITEMS_LIST = "redirect:/shop/itemsList";
    @Autowired
    private ModelMapper modelMapper;


    private ItemService itemService;

    private UserService userService;

    private OrderService orderService;

    @Autowired
    public ShopController(ItemService itemService, UserService userService, OrderService orderService) {
        this.itemService = itemService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/loginHome")
    public String afterLogin(){
        return "after-login";
    }

    @GetMapping("/itemsList")
    public String itemsList(Model model){
        List<ItemDto> items = itemService.findAll();
        model.addAttribute("items",items);
        return "items-list";
    }

    @GetMapping("/usersList")
    public String userstList(Model model){
        List<UserDto> users = userService.findAll();
        model.addAttribute("users",users);
        return "users-list";
    }

    @GetMapping("/showFormForAddItem")
    public String showFormForAddItem(Model model){
        ItemDto item = new ItemDto();
        model.addAttribute("item",item);
        return ITEM_FORM;
    }

    @GetMapping("/showFormForUpdateItem")
    public String updateItem(@RequestParam("itemId") int itemId, Model model){
        Item item = itemService.findById(itemId);
        ItemDto itemDto = modelMapper.map(item,ItemDto.class);
        model.addAttribute("item",itemDto);
        return ITEM_FORM;
    }

    @PostMapping("/saveItem")
    public String saveItem(@Valid @ModelAttribute("item") ItemDto itemDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return ITEM_FORM;
        }
        Item item = modelMapper.map(itemDto,Item.class);
        itemService.saveItem(item);
        return REDIRECT_SHOP_ITEMS_LIST;
    }

    @GetMapping("/deleteItem")
    public String deleteItem(@RequestParam("itemId") int itemId){
        try {
            itemService.deleteById(itemId);
        } catch (Exception e){
            return "file-in-use-exception";
        }
        return REDIRECT_SHOP_ITEMS_LIST;
    }

    @GetMapping("/addItemToUser")
    public String addItemToUser(@RequestParam("userName") String userName, @RequestParam("itemId") int itemId, Model model){
        User user = userService.findUserByName(userName);
        Item item = itemService.findById(itemId);
        Order order;
        Order result= orderService.findOrder(user.getId(),item.getId());
        if(result == null){
            order = new Order(user,item,1);
        }else{
            order = result;
            order.increaseQuantity();
        }
        item.setQuantity(item.getQuantity()-1);
        itemService.saveItem(item);
        orderService.saveOrder(order);
        return REDIRECT_SHOP_ITEMS_LIST;
    }

    @GetMapping("/userOrdersList")
    public String userOrderList(@RequestParam("userName") String userName, Model model){
        User user = userService.findUserByName(userName);
        List<Order> orders = orderService.getUserOrders(user.getId());
        model.addAttribute("orders",orders);
        return "view-orders";
    }

    @GetMapping("/deleteOrder")
    public String addItemToUser(@RequestParam("orderId") int orderId, @RequestParam("userName") String userName, Model model){
        Order order = orderService.findById(orderId);
        Item item = order.getItem();
        item.setQuantity(order.getQuantity()+item.getQuantity());
        itemService.saveItem(item);
        orderService.deleteById(orderId);
        return "redirect:/shop/userOrdersList?userName="+userName;
    }

}
