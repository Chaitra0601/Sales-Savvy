package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entitys.CartItem;
import com.example.demo.Entitys.Product;
import com.example.demo.Entitys.ProductImage;
import com.example.demo.Entitys.User;
import com.example.demo.Repositorys.CartItemRepository;
import com.example.demo.Repositorys.ProductImageRepository;
import com.example.demo.Repositorys.ProductRepository;
import com.example.demo.Repositorys.UserRepository;

import java.util.*;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
   private ProductImageRepository productImageRepository;
    
    @Autowired
    private ProductRepository productRepository;

    
    public void addCartItem(int userId, int productId, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Fetch cart item for this userId and productId
        Optional<CartItem> existingItem = cartRepository.findByUserIdAndProductId(userId, productId);

        if (existingItem.isPresent()) {
            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartRepository.save(cartItem);
        } else {
            CartItem newItem = new CartItem(user, product, quantity);
            cartRepository.save(newItem);
        }
    }
    
    // Get Cart Items for a User
    
    public int getCartItemCount(@RequestParam int userId) {
    	return cartRepository.getTotalCount(userId);
    }
    
    public Map<String, Object> getCartItems(int userId) {
        // Fetch the cart items for the user with product details
        List<CartItem> cartItems = cartRepository.findCartItemsWithProductDetails(userId);
        // Create a response map to hold the cart details
        Map<String, Object> response = new HashMap<>();
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        response.put("username", user.getUsername());
        response.put("role", user.getRole().name());
        
     // List to hold the product details
        List<Map<String, Object>> products = new ArrayList<>();
        int overallTotalPrice = 0;

        for (CartItem cartItem : cartItems) {
            Map<String, Object> productDetails = new HashMap<>();
            // Get product details
            Product product = cartItem.getProduct();
            // Fetch product images
            List<ProductImage> productImages = productImageRepository.findByProduct_Productid(product.getProductid());
            String imageUrl = productImages.get(0).getImageurl();
            // Populate product details
            productDetails.put("id", product.getProductid());
            productDetails.put("image_url", imageUrl);
            productDetails.put("name", product.getName());
            productDetails.put("description", product.getDescription());
            productDetails.put("price_per_unit", product.getPrice());
            productDetails.put("quantity", cartItem.getQuantity());
            productDetails.put("total_price", cartItem.getQuantity() * product.getPrice().doubleValue());

            // Add to products list
            products.add(productDetails);
            // Update overall total price
            overallTotalPrice += cartItem.getQuantity() * product.getPrice().doubleValue();
        }
        // Prepare the final cart response
        Map<String, Object> cart = new HashMap<>();
        cart.put("products", products);
        cart.put("overall_total_price", overallTotalPrice);

        response.put("cart", cart);

        return response;
    } 

      public void updateCartItemQuantity(int userId, int productId, int quantity) {
         
            // Fetch cart item for this userId and productId
            Optional<CartItem> existingItem = cartRepository.findByUserIdAndProductId(userId, productId);

            if (existingItem.isPresent()) {
                if (quantity == 0) {
                    deleteCartItem(userId, productId);
                } else {
                	CartItem cartItem=existingItem.get();
                    cartItem.setQuantity(quantity);
                    cartRepository.save(cartItem);
                }
        }
   }
            public void deleteCartItem(int userId, int productId) {
                cartRepository.deleteCartItem(userId, productId);
     
    }
}

