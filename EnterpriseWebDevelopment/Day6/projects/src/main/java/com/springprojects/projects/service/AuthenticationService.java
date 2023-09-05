package com.springprojects.projects.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springprojects.projects.entity.User;
import com.springprojects.projects.repository.UserRepository;

@Service
public class AuthenticationService {
	
    @Autowired
    private UserRepository userRepository;

    public boolean authenticate(String username, String password) {
        // Check if the user exists in the database
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Compare the provided password with the stored password (you may need to hash and salt it)
            if (password.equals(user.getPassword())) {
                // Authentication succeeded
                return true;
            }
        }

        // Authentication failed
        return false;
    }
}
