package com.example.demo.Repositorys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Entitys.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByCategoryname(String categoryname);
}
