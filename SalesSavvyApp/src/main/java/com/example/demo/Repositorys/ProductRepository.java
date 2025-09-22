package com.example.demo.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Entitys.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory_Categoryid(Integer categoryId);
    
    @Query("SELECT p.category.categoryname FROM Product p WHERE p.productid = :productId")
    String findCategoryNameByProductId(int productId);
}
