package com.example.demo.Entitys;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productid;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(precision = 19, scale = 4, nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;
   
    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updated_at  = LocalDateTime.now();

	/**
	 * 
	 */
	public Product() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param productid
	 * @param name
	 * @param description
	 * @param price
	 * @param stock
	 * @param category
	 * @param created_at
	 * @param updated_at
	 */
	public Product(Integer productid, String name, String description, BigDecimal price, Integer stock,
			Category category, LocalDateTime created_at, LocalDateTime updated_at) {
		super();
		this.productid = productid;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.category = category;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	/**
	 * @param name
	 * @param description
	 * @param price
	 * @param stock
	 * @param category
	 * @param created_at
	 * @param updated_at
	 */
	public Product(String name, String description, BigDecimal price, Integer stock, Category category,
			LocalDateTime created_at, LocalDateTime updated_at) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.category = category;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

}
