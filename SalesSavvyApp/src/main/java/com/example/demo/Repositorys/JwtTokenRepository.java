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
    Optional<JwtToken> findByToken(String token);
    @Query("SELECT t FROM JwtToken t where t.user.userid = :userid")
    JwtToken findByUserid(@Param("userid") int userid);
    
    // Custom query to delete tokens by user ID
    @Modifying
    @Transactional
    @Query("DELETE FROM JwtToken t WHERE t.user.userid = :userid")
    JwtToken deleteByUserid(@Param("userid") int userid);
    
}
