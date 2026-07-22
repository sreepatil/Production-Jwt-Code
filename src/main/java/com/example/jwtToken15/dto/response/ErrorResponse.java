package com.example.jwtToken15.dto.response;

import java.time.LocalDateTime;

public record ErrorResponse(String description, int status, String error, String message, LocalDateTime timestamp) {
}
