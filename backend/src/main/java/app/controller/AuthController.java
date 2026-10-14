package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.models.User;
import app.services.UserService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService service;

    // Open Register Endpoint
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    // Open Login Endpoint
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Register User
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        return service.register(user);
    }

    // Login User
    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        return service.login(user);
    }
}
