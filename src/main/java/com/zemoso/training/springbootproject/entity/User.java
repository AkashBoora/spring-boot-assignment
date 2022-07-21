package com.zemoso.training.springbootproject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    @NotEmpty(message = "User name should not be null")
    private String userName;

    @Column(name = "password")
    @NotEmpty(message = "User password should not be empty")
    private String password;

    @Column(name = "enabled")
    private int enabled;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Authority authority;


    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(int id, String userName, String password, int enabled, Authority authority) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
        this.authority = authority;
    }
}
