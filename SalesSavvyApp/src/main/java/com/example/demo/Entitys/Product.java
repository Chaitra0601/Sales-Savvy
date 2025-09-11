package com.example.demo.Entitys;
import jakarta.persistence.*;
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

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

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
	 * @param createdAt
	 * @param updatedAt
	 */
	public Product(Integer productid, String name, String description, Double price, Integer stock, Category category,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.productid = productid;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.category = category;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	/**
	 * @param name
	 * @param description
	 * @param price
	 * @param stock
	 * @param category
	 * @param createdAt
	 * @param updatedAt
	 */
	public Product(String name, String description, Double price, Integer stock, Category category,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.category = category;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Product [productid=" + productid + ", name=" + name + ", description=" + description + ", price="
				+ price + ", stock=" + stock + ", category=" + category + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}
}
