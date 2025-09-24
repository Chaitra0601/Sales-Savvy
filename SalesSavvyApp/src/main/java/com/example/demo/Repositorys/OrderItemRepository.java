package com.example.demo.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entitys.OrderItem;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    @Query("SELECT oi FROM OrderItem oi WHERE oi.order.orderid = :orderId")
    List<OrderItem> findByOrderid(String orderId);

    @Query("SELECT oi FROM OrderItem oi WHERE oi.order.user.userid = :userId AND oi.order.status = 'SUCCESS'")
    List<OrderItem> findSuccessfulOrderItemsByUserid(@Param("userId") int userId);
}