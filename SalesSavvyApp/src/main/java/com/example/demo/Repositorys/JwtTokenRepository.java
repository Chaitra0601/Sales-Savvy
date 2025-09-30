package com.example.demo.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.Entitys.JwtToken;

import jakarta.transaction.Transactional;

import java.util.Optional;

@Repository
public interface JwtTokenRepository extends JpaRepository<JwtToken, Integer> {
   //Finde a token by its value
	Optional<JwtToken> findByToken(String token);
	
	//Custom query to find tokens by user ID
    @Query("SELECT t FROM JwtToken t where t.user.userid = :userId")
    JwtToken findByUserid(@Param("userId") int userId);
    
    // Custom query to delete tokens by user ID
    @Modifying
    @Transactional
    @Query("DELETE FROM JwtToken t WHERE t.user.userid = :userId")
    int deleteByUserid(@Param("userId") int userId);
    
}
