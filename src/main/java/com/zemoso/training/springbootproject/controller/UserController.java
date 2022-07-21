package com.zemoso.training.springbootproject.controller;

import com.zemoso.training.springbootproject.dto.UserDto;
import com.zemoso.training.springbootproject.service.ItemService;
import com.zemoso.training.springbootproject.service.OrderService;
import com.zemoso.training.springbootproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/user")
public class UserController {

    public static final String USER_FORM = "user-form";

    @Autowired
    private ModelMapper modelMapper;

    private UserService userService;

    @Autowired
    public UserController(ItemService itemService, UserService userService, OrderService orderService) {
        this.userService = userService;
    }


    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "access-denied";
    }

    @GetMapping("/showFormForAddUser")
    public String showFormForAddUser(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user",userDto);
        return USER_FORM;
    }

    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return USER_FORM;
        }
        userService.saveUserDto(userDto);
        return "redirect:/shop/home";
    }
}
