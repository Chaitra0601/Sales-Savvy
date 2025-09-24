package com.example.demo.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.Entitys.CartItem;
import com.example.demo.Entitys.Product;
import com.example.demo.Entitys.User;
import jakarta.transaction.Transactional;
import java.util.*;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Integer> {
    
	Optional<CartItem> findByUserAndProduct(User user, Product product);
	@Query("SELECT c FROM CartItem c WHERE c.user.userid = :userId AND c.product.productid = :productId")
	 Optional<CartItem> findByUserIdAndProductId(int userId, int productId);
	
	@Query("SELECT COALESCE(SUM(c.quantity), 0) FROM CartItem c WHERE c.user.userid = :userId")
	int getTotalCount(int userId);
	
	@Query("SELECT c FROM CartItem c JOIN FETCH c.product p LEFT JOIN FETCH ProductImage pi ON  pi.product.productid = p.productid WHERE c.user.userid = :userId")
	List<CartItem> findCartItemsWithProductDetails(int userId);
	
	
	@Query("UPDATE CartItem c SET c.quantity = :quantity WHERE c.id = :cartItemId")
	void updateCartItemQuantity(int cartItemId, int quantity);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM CartItem c WHERE c.user.userid = :userId AND c.product.productid = :productId")
	void deleteCartItem(int userId, int productId);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM CartItem c WHERE c.user.userid = :userId")
	void deleteAllCartItemsByUserId(int userId);
}