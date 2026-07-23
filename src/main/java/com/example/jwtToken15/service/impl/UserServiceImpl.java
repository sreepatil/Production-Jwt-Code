package com.example.jwtToken15.service.impl;

import com.example.jwtToken15.dto.request.AuthRequest;
import com.example.jwtToken15.dto.request.RegisterRequest;
import com.example.jwtToken15.dto.response.AuthResponse;
import com.example.jwtToken15.dto.response.UserResponse;
import com.example.jwtToken15.entity.User;
import com.example.jwtToken15.enums.Role;
import com.example.jwtToken15.exception.UserAlreadyExistsException;
import com.example.jwtToken15.service.UserService;
import com.example.jwtToken15.jwt.JwtService;
import com.example.jwtToken15.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtService jwtService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    @Override
    public UserResponse register(RegisterRequest request) {

        log.debug("Processing registration request for username: {}",
                request.username());

        if (userRepository.existsByUsername(request.username())){

            log.warn("Registration rejected because username already exists : {}",
                    request.username());

            throw new UserAlreadyExistsException("Username " + request.username() + " Already Exists");
        }

//        User user = new User();
//        user.setUsername(request.username());
//        user.setPassword(passwordEncoder.encode(request.password()));
//        user.setRole(Role.USER);

        User user = User.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();

        User savedUser = userRepository.save(user);


//        return new UserResponse(savedUser.getId(),
//                savedUser.getUsername(),
//                savedUser.getRole());

        log.info("User registered successfully: id={}",
                savedUser.getId());

        return new UserResponse(savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getRole().name());
    }

    @Override
    public AuthResponse login(AuthRequest request) {

        log.debug("Processing login request for username: {}",
                request.username());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        log.info("User Authenticated successfully: {}",
                request.username());

        User user = userRepository.findByUsername(request.username()).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));

        String token = jwtService.generateToken(user.getUsername(), user.getRole());

        return new AuthResponse(token, "Bearer");
    }
}
