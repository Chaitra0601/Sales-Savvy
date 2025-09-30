package com.example.demo.AdminServices;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.example.demo.Entitys.Role;
import com.example.demo.Entitys.User;
import com.example.demo.Repositorys.JwtTokenRepository;
import com.example.demo.Repositorys.UserRepository;

import java.util.Optional;

@Service
public class AdminUserService {
     UserRepository userRepository;
     JwtTokenRepository jwtTokenRepository;

    public AdminUserService(UserRepository userRepository, JwtTokenRepository jwtTokenRepository) {
        this.userRepository = userRepository;
        this.jwtTokenRepository = jwtTokenRepository;
    }
    
    public User getUserById(Integer userId) {
    	return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("user Not Found"));
    }

    @Transactional
    public User modifyUser(Integer userId, String username, String email, String role) {
        // Check if the user exists
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        } else {
            User existingUser = userOptional.get();
            existingUser.setUsername(username);
            existingUser.setEmail(email);
            existingUser.setRole(Role.valueOf(role));
            
        // Delete associated JWT tokens
        jwtTokenRepository.deleteByUserid(userId);
        // Save updated user
        return userRepository.save(existingUser);
    }
  }
}


