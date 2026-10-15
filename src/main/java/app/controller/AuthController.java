
package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String result = service.register(user);

        if ("Registration Successful".equals(result)) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        String result = service.login(user);

        if ("Login Successful".equals(result)) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
}

