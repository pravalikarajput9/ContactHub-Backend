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

    // Check registration endpoint
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    // Check login endpoint
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Register a new user
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        return service.register(user);
    }

    // Login an existing user
    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        return service.login(user);
    }
}
