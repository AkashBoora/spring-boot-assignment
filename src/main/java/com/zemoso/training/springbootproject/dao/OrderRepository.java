package com.zemoso.training.springbootproject.dao;

import com.zemoso.training.springbootproject.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Query(value = "select * from orders where user_id = :userId and item_id = :itemId",nativeQuery = true)
    public Order findOrder(int userId, int itemId);

    @Query(value = "select * from orders where user_id = ?1",nativeQuery = true)
    List<Order> getUserOrders(int userId);
}
