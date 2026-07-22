package com.example.jwtToken15.dto.response;

public record UserResponse(
        Long id,
        String username,
        String role
) {
}
