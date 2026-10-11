package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import app.models.User;
import app.services.UserService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService service;

    // Open Register Page
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    // Open Login Page
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Register User
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        service.registerUser(user);
        return "User registered successfully";
    }
}