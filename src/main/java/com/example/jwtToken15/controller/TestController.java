package com.example.jwtToken15.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/private/admin")
    public String admin(){
        return "Hello Admin";
    }

    @GetMapping("/user/hello")
    public String user(){
        return "Hello User";
    }
}
