package com.zemoso.training.springbootproject.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "authorities")
@Getter
@Setter
@AllArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    @NotEmpty(message = "User name should not be null")
    private String userName;

    @Column(name = "authority")
    @NotEmpty(message = "Authority should not be empty")
    private String authorityType;


    public Authority() {
    }

    public Authority(String userName, String authorityType) {
        this.userName = userName;
        this.authorityType = authorityType;
    }
}
