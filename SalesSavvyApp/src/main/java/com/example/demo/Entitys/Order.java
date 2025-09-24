package com.example.demo.Entitys;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "orderid")
    private String orderid; // UUID or custom ID

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @Column(precision = 38, scale = 2, name = "totalamount", nullable = false)
    private BigDecimal totalamount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime created_at = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updated_at  = LocalDateTime.now();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

	
	public Order() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param orderid
	 * @param user
	 * @param totalamount
	 * @param status
	 * @param created_at
	 * @param updated_at
	 * @param orderItems
	 */
	public Order(String orderid, User user, BigDecimal totalamount, OrderStatus status, LocalDateTime created_at,
			LocalDateTime updated_at, List<OrderItem> orderItems) {
		super();
		this.orderid = orderid;
		this.user = user;
		this.totalamount = totalamount;
		this.status = status;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.orderItems = orderItems;
	}


	/**
	 * @param user
	 * @param totalamount
	 * @param status
	 * @param created_at
	 * @param updated_at
	 * @param orderItems
	 */
	public Order(User user, BigDecimal totalamount, OrderStatus status, LocalDateTime created_at,
			LocalDateTime updated_at, List<OrderItem> orderItems) {
		super();
		this.user = user;
		this.totalamount = totalamount;
		this.status = status;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.orderItems = orderItems;
	}


	public String getOrderid() {
		return orderid;
	}


	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public BigDecimal getTotalamount() {
		return totalamount;
	}


	public void setTotalamount(BigDecimal totalamount) {
		this.totalamount = totalamount;
	}


	public OrderStatus getStatus() {
		return status;
	}


	public void setStatus(OrderStatus status) {
		this.status = status;
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


	public List<OrderItem> getOrderItems() {
		return orderItems;
	}


	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

}