package com.zemoso.training.springbootproject.dto;

import com.zemoso.training.springbootproject.entity.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int id;

    @NotEmpty(message = "User name should not be null")
    private String userName;

    @NotEmpty(message = "User password should not be empty")
    private String password;

    private int enabled;

    private Authority authority;

}
