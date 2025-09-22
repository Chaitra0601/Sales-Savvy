package com.example.demo.Repositorys;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Entitys.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByCategoryname(String categoryname);
}
