package com.example.demo.AdminControllers;

import com.example.demo.AdminServices.AdminProductService;
import com.example.demo.Entitys.Product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/admin/products")
@CrossOrigin(origins = "http://localhost:5174" , allowCredentials = "true")
public class AdminProductController {


	   AdminProductService adminProductService;

	    public AdminProductController(AdminProductService adminProductService) {
	        this.adminProductService = adminProductService;
	    }

	    @PostMapping("/add")
	    public ResponseEntity<?> addProduct(@RequestBody Map<String, Object> productRequest) {
	        try {
	            String name = (String) productRequest.get("name");
	            String description = (String) productRequest.get("description");
	            Double price = Double.valueOf(String.valueOf(productRequest.get("price")));
	            Integer stock = (Integer) productRequest.get("stock");
	            Integer categoryId = (Integer) productRequest.get("categoryId");
	            String imageUrl = (String) productRequest.get("imageUrl");

	            Product addedProduct = adminProductService.addProductWithImage(name, description, price, stock,
	                    categoryId, imageUrl);
	            return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
	        }
	        catch (Exception e) {
	            e.printStackTrace(); // ✅ log exact error in console
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
	        }
	}
	    @DeleteMapping("/delete")
	    public ResponseEntity<?> deleteProduct(@RequestBody Map<String, Integer> requestBody) {
	        try {
	            Integer productId = requestBody.get("productId");
	            adminProductService.deleteProduct(productId);
	            return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ProductId, Deletion Failed");
	        } 
	    }

	
	
}
