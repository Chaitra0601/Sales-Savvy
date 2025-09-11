package com.example.demo.Entitys;
import jakarta.persistence.*;

@Entity
@Table(name = "productimages")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageid;

    @ManyToOne
    @JoinColumn(name = "productid", nullable = false)
    private Product product;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String imageurl;

	/**
	 * 
	 */
	public ProductImage() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param imageid
	 * @param product
	 * @param imageurl
	 */
	public ProductImage(Integer imageid, Product product, String imageurl) {
		super();
		this.imageid = imageid;
		this.product = product;
		this.imageurl = imageurl;
	}

	/**
	 * @param product
	 * @param imageurl
	 */
	public ProductImage(Product product, String imageurl) {
		super();
		this.product = product;
		this.imageurl = imageurl;
	}

	public Integer getImageid() {
		return imageid;
	}

	public void setImageid(Integer imageid) {
		this.imageid = imageid;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	@Override
	public String toString() {
		return "ProductImage [imageid=" + imageid + ", product=" + product + ", imageurl=" + imageurl + "]";
	}

    
}
