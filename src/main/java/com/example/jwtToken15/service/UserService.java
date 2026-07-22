package com.example.jwtToken15.service;

import com.example.jwtToken15.dto.request.AuthRequest;
import com.example.jwtToken15.dto.request.RegisterRequest;
import com.example.jwtToken15.dto.response.AuthResponse;
import com.example.jwtToken15.dto.response.UserResponse;

public interface UserService {

    UserResponse register(RegisterRequest request);

    AuthResponse login(AuthRequest request);
}
