package com.example.demo.AdminServices;

import org.springframework.stereotype.Service;

import com.example.demo.Entitys.Category;
import com.example.demo.Entitys.Product;
import com.example.demo.Entitys.ProductImage;
import com.example.demo.Repositorys.CategoryRepository;
import com.example.demo.Repositorys.ProductImageRepository;
import com.example.demo.Repositorys.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AdminProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final CategoryRepository categoryRepository;

    public AdminProductService(ProductRepository productRepository, ProductImageRepository productImageRepository,
                               CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
        this.categoryRepository = categoryRepository;
    }

    public Product addProductWithImage(String name, String description, Double price, Integer stock, Integer categoryId, String imageUrl) {
        // Validate the category
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw new IllegalArgumentException("Invalid category ID");
        }
        
     // Create and save the product
       /* Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(BigDecimal.valueOf(price));
        product.setStock(stock);
        product.setCategory(category.get());
        product.setCreated_at(LocalDateTime.now());
        product.setUpdated_at(LocalDateTime.now()); */
        
        Product product = new Product(name, description, BigDecimal.valueOf(price), stock, category.get(), LocalDateTime.now(), LocalDateTime.now());
        Product savedProduct = productRepository.save(product);

        // Create and save the product image
        if (imageUrl != null && !imageUrl.isEmpty()) {
            ProductImage productImage = new ProductImage();
            productImage.setProduct(savedProduct);
            productImage.setImageurl(imageUrl);
            productImageRepository.save(productImage);
        } else {
            throw new IllegalArgumentException("Product image URL cannot be empty");
        }

        return savedProduct;
        }

        public void deleteProduct(Integer productId) {
            // Check if the product exists
        	Optional<Product> existingProduct=productRepository.findById(productId);
            if (!existingProduct.isPresent()) {
                throw new IllegalArgumentException("Product not found");
            } else {
            	Product product=existingProduct.get();
            // Delete associated product images
            productImageRepository.deleteByProductId(productId);
            // Delete the product
            productRepository.delete(product);
        }
        }
}
