package com.example.jwtToken15.service;

import com.example.jwtToken15.dto.request.AuthRequest;
import com.example.jwtToken15.dto.request.RegisterRequest;
import com.example.jwtToken15.dto.request.UpdatetUserRequest;
import com.example.jwtToken15.dto.response.AuthResponse;
import com.example.jwtToken15.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface UserService {

    UserResponse register(RegisterRequest request);

    AuthResponse login(AuthRequest request);

    ResponseEntity<?> updatedUser(
            UpdatetUserRequest request,
            Authentication authentication
    );
}
