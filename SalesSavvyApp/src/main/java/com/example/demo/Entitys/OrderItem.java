package com.example.demo.Entitys;
import jakarta.persistence.*;

@Entity
@Table(name = "orderitems")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "orderid", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "productid", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double priceperunit;

    @Column(nullable = false)
    private Double totalprice;

	/**
	 * 
	 */
	public OrderItem() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param order
	 * @param product
	 * @param quantity
	 * @param priceperunit
	 * @param totalprice
	 */
	public OrderItem(Integer id, Order order, Product product, Integer quantity, Double priceperunit,
			Double totalprice) {
		super();
		this.id = id;
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.priceperunit = priceperunit;
		this.totalprice = totalprice;
	}

	/**
	 * @param order
	 * @param product
	 * @param quantity
	 * @param priceperunit
	 * @param totalprice
	 */
	public OrderItem(Order order, Product product, Integer quantity, Double priceperunit, Double totalprice) {
		super();
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.priceperunit = priceperunit;
		this.totalprice = totalprice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPriceperunit() {
		return priceperunit;
	}

	public void setPriceperunit(Double priceperunit) {
		this.priceperunit = priceperunit;
	}

	public Double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", order=" + order + ", product=" + product + ", quantity=" + quantity
				+ ", priceperunit=" + priceperunit + ", totalprice=" + totalprice + "]";
	}

    
}
