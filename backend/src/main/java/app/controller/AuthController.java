package app.controller;

import app.models.User;
import app.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @GetMapping("/register")
    public ResponseEntity<String> registerPage() {
        return ResponseEntity.ok("ContactHub registration API is running");
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginPage() {
        return ResponseEntity.ok("ContactHub login API is running");
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String result = service.register(user);

        if ("Registration Successful".equals(result)) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        String result = service.login(user);

        if ("Login Successful".equals(result)) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.status(401).body(result);
    }
}
