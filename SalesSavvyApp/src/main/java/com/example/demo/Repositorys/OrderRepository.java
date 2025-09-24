package com.example.demo.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Entitys.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    //Custom query methods can be added here if neded
}
