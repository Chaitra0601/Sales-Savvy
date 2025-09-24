package com.example.demo.Entitys;
import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "orderitems")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderid", nullable = false)
    private Order order;
    
    @ManyToOne
    @JoinColumn(name = "productid", nullable = false)
    private  Product product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(precision = 19, scale = 4, name = "priceperunit", nullable = false)
    private BigDecimal priceperunit;

    @Column(precision = 19, scale = 4, name = "totalprice", nullable = false)
    private BigDecimal totalprice;

	/**
	 * 
	 */
	public OrderItem() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param orderid
	 * @param product
	 * @param quantity
	 * @param priceperunit
	 * @param totalprice
	 */
	public OrderItem(int id, Order order, Product product, int quantity, BigDecimal priceperunit,
			BigDecimal totalprice) {
		super();
		this.id = id;
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.priceperunit = priceperunit;
		this.totalprice = totalprice;
	}

	/**
	 * @param orderid
	 * @param product
	 * @param quantity
	 * @param priceperunit
	 * @param totalprice
	 */
	public OrderItem(Order order, Product product, int quantity, BigDecimal priceperunit, BigDecimal totalprice) {
		super();
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.priceperunit = priceperunit;
		this.totalprice = totalprice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPriceperunit() {
		return priceperunit;
	}

	public void setPriceperunit(BigDecimal priceperunit) {
		this.priceperunit = priceperunit;
	}

	public BigDecimal getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}

}
