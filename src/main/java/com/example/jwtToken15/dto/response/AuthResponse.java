package com.example.jwtToken15.dto.response;

public record AuthResponse(
        String token,
        String tokenType
) {
}
