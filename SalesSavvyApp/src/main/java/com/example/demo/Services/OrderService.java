package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entitys.OrderItem;
import com.example.demo.Entitys.Product;
import com.example.demo.Entitys.ProductImage;
import com.example.demo.Entitys.User;
import com.example.demo.Repositorys.OrderItemRepository;
import com.example.demo.Repositorys.ProductImageRepository;
import com.example.demo.Repositorys.ProductRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    /**
     * Fetches all successful orders for a given user and returns the required response format.
     *
     * @param user The authenticated user object.
     * @return A map containing the user's role, username, and ordered products.
     */
    public Map<String, Object> getOrdersForUser(User user) {
        // Fetch all successful order items for the user
        List<OrderItem> orderItems = orderItemRepository.findSuccessfulOrderItemsByUserid(user.getUserid());

        // Prepare the response map
        Map<String, Object> response = new HashMap<>();
        response.put("username", user.getUsername());
        response.put("role", user.getRole()); // Directly use the role as it is an enum mapped to a string

        // Transform order items into a list of product details
        List<Map<String, Object>> products = new ArrayList<>();
        for (OrderItem item : orderItems) {
            Product product = item.getProduct();
            if (product == null) {
                continue; // Skip if the product does not exist
            }
            // Fetch the product image (if available)
            List<ProductImage> images = productImageRepository.findByProduct_Productid(product.getProductid());
            String imageUrl = images.isEmpty() ? null : images.get(0).getImageurl();

            // Create a product details map
            Map<String, Object> productDetails = new HashMap<>();
            productDetails.put("order_id", item.getOrder().getOrderid());
            productDetails.put("quantity", item.getQuantity());
            productDetails.put("total_price", item.getTotalprice());
            productDetails.put("product_id", product.getProductid());
            productDetails.put("name", product.getName());
            productDetails.put("description", product.getDescription());
            productDetails.put("price_per_unit", item.getPriceperunit());
            productDetails.put("imageUrl", imageUrl);

            products.add(productDetails);
        }

        // Add the products list to the response
        response.put("products", products);

        return response;
    }
}

