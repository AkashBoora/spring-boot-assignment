package com.zemoso.training.springbootproject.service;

import com.zemoso.training.springbootproject.dto.UserDto;
import com.zemoso.training.springbootproject.entity.User;

import java.util.List;


public interface UserService {
    public List<UserDto> findAll();


    public User findById(int userId);

    public void saveUser(User user);

    public void saveUserDto(UserDto userDto);

    public User findUserByName(String userName);
}
