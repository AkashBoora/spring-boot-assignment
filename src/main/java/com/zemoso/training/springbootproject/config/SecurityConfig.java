package com.zemoso.training.springbootproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String ADMIN = "ADMIN";
    @Autowired
    private DataSource dataSource;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/shop/home").permitAll()
                .antMatchers("/shop/itemList").permitAll()
                .antMatchers("/shop/usersList").hasRole(ADMIN)
                .antMatchers("/shop/showFormForAddItem").hasRole(ADMIN)
                .antMatchers("/shop/showFormForUpdateItem").hasRole(ADMIN)
                .antMatchers("/shop/addItemToUser").hasRole("USER")
                .and()
                .formLogin()
                .defaultSuccessUrl("/shop/loginHome")
                .and()
                .logout()
                .logoutSuccessUrl("/shop/home").permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/user/accessDenied");
    }
}
