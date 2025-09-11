package com.example.demo.Entitys;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private String orderid; // UUID or custom ID

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @Column(nullable = false)
    private Double totalamount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.pending;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

	/**
	 * 
	 */
	public Order() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param orderid
	 * @param user
	 * @param totalamount
	 * @param status
	 * @param createdAt
	 * @param updatedAt
	 * @param items
	 */
	public Order(String orderid, User user, Double totalamount, OrderStatus status, LocalDateTime createdAt,
			LocalDateTime updatedAt, List<OrderItem> items) {
		super();
		this.orderid = orderid;
		this.user = user;
		this.totalamount = totalamount;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.items = items;
	}

	/**
	 * @param user
	 * @param totalamount
	 * @param status
	 * @param createdAt
	 * @param updatedAt
	 * @param items
	 */
	public Order(User user, Double totalamount, OrderStatus status, LocalDateTime createdAt, LocalDateTime updatedAt,
			List<OrderItem> items) {
		super();
		this.user = user;
		this.totalamount = totalamount;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.items = items;
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

	public Double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", user=" + user + ", totalamount=" + totalamount + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}
}
