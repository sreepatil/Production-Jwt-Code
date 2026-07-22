package com.example.jwtToken15.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 50, message = "Username should be between 3 to 50 characters ")
        String username,

        @NotBlank(message = "Password is required")
        @Size(min = 8 , max = 30, message = "Password should be between 8 to 30 characters")
        String password) {
}
