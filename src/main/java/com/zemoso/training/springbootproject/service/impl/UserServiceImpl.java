package com.zemoso.training.springbootproject.service.impl;

import com.zemoso.training.springbootproject.dao.UserRepository;
import com.zemoso.training.springbootproject.dto.UserDto;
import com.zemoso.training.springbootproject.entity.Authority;
import com.zemoso.training.springbootproject.entity.User;
import com.zemoso.training.springbootproject.exceptions.ObjectNotFoundException;
import com.zemoso.training.springbootproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        User admin = findById(1);
        users.remove(admin);
        for(User user:users){
            userDtos.add(modelMapper.map(user,UserDto.class));
        }
        return userDtos;
    }

    @Override
    public User findById(int userId) {
        Optional<User> result = userRepository.findById(userId);
        User user =null;
        if(result.isPresent()){
            user = result.get();
        }else{
            throw new ObjectNotFoundException("Did not find Object for id - "+userId);
        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Authority authority = new Authority(user.getUserName(),"ROLE_USER");
        user.setAuthority(authority);
        userRepository.save(user);
    }

    @Override
    public User findUserByName(String userName) {
        return userRepository.findUserByName(userName);
    }
}
