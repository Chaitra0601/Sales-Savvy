package com.example.demo.Entitys;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryid;

    @Column(nullable = false, unique = true, length = 255)
    private String categoryname;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    // No-arg constructor (required by Hibernate)
    public Category() {}

    public Category(Integer categoryid, String categoryname, List<Product> products) {
        this.categoryid = categoryid;
        this.categoryname = categoryname;
        this.products = products;
    }

    public Category(String categoryname, List<Product> products) {
        this.categoryname = categoryname;
        this.products = products;
    }

    // Getters & setters
    public Integer getCategoryid() { return categoryid; }
    public void setCategoryid(Integer categoryid) { this.categoryid = categoryid; }

    public String getCategoryname() { return categoryname; }
    public void setCategoryname(String categoryname) { this.categoryname = categoryname; }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }

    @Override
    public String toString() {
        return "Category [categoryid=" + categoryid + ", categoryname=" + categoryname + "]";
    }
}
