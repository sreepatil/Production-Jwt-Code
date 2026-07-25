package com.example.jwtToken15.controller;

import com.example.jwtToken15.dto.request.UpdatetUserRequest;
import com.example.jwtToken15.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdatetUserRequest request,
                                        Authentication authentication){
        return userService.updatedUser(request, authentication);
    }
}
