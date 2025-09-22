package com.example.demo.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entitys.User;
import com.example.demo.Repositorys.UserRepository;
import com.example.demo.Services.CartService;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5174", allowCredentials = "true")
@RequestMapping("/api/cart")
public class CartController {

	    @Autowired
	    private CartService cartService;
	    
	    @Autowired
	    private UserRepository userRepository;
	    
	    @PostMapping("/add")
	    public ResponseEntity<Void> addCartItem(@RequestBody Map<String, Object> request) {
	        String username = (String) request.get("username");
	        int productId = ((Number) request.get("productId")).intValue();

	        // Handle quantity: Default is 1 if not provided
	        int quantity = (request.containsKey("quantity") ? (Number) request.get("quantity") : 1).intValue();

	        // Fetch the user using username
	        User user = userRepository.findByUsername(username)
	                .orElseThrow(() -> new RuntimeException("User not present"));

	        // Add the product to the cart
	        cartService.addCartItem(user.getUserid(), productId, quantity);
	        return ResponseEntity.status(HttpStatus.CREATED).build();
	    }
	    
	    @GetMapping("/items/count")
		public ResponseEntity<Integer> getCartCount(@RequestParam String username) {
			User user = userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("User Not Found"));
			int count  = cartService.getCartItemCount(user.getUserid());
			return ResponseEntity.ok(count);
	    }
	    
	    // Fetch all cart items for the user (based on username)
	    @GetMapping("/items")
	   public ResponseEntity<Map<String, Object>> getCartItems(@RequestParam String username) {
	        User user = userRepository.findByUsername(username)
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        Map<String, Object> cartItems = cartService.getCartItems(user.getUserid());
	        return ResponseEntity.ok(cartItems);
	    }
	    
	    @PutMapping("/update")
	    public ResponseEntity<Void> updateCartItemQuantity(@RequestBody Map<String, Object> request) {
	            String username = (String) request.get("username");
	            int productId = ((Number) request.get("productId")).intValue();
	            int quantity = ((Number) request.get("quantity")).intValue();
	            // Fetch user using username
	            User user = userRepository.findByUsername(username)
	                    .orElseThrow(() -> new RuntimeException("User not found"));
	            // Update the cart item quantity
	            cartService.updateCartItemQuantity(user.getUserid(), productId, quantity);
	            return ResponseEntity.status(HttpStatus.OK).build();
	     }

	     @DeleteMapping("/delete")
	     public ResponseEntity<Void> deleteCartItem(@RequestBody Map<String, Object> request) {
	            String username = (String) request.get("username");
	            int productId = ((Number) request.get("productId")).intValue();
	            // Fetch the user using username
	            User user = userRepository.findByUsername(username)
	                    .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

	            // Delete the cart item
	            cartService.deleteCartItem(user.getUserid(), productId);
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	        }
}
