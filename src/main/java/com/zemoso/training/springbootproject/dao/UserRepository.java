package com.zemoso.training.springbootproject.dao;

import com.zemoso.training.springbootproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value = "select * from users where userName = ?1",nativeQuery = true)
    public User findUserByName(String username);
}
