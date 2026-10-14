package app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.models.User;
import app.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    // Register a user
    public String register(User user) {
        if (user == null
                || user.getEmail() == null
                || user.getEmail().isBlank()
                || user.getPassword() == null
                || user.getPassword().isBlank()) {
            return "Email and password are required";
        }

        if (repo.existsById(user.getEmail())) {
            return "Email already registered";
        }

        repo.save(user);
        return "Registration Successful";
    }

    // Login a user
    public String login(User user) {
        if (user == null
                || user.getEmail() == null
                || user.getPassword() == null) {
            return "Invalid Email or Password";
        }

        User dbUser = repo.findById(user.getEmail()).orElse(null);

        if (dbUser != null
                && dbUser.getPassword() != null
                && dbUser.getPassword().equals(user.getPassword())) {
            return "Login Successful";
        }

        return "Invalid Email or Password";
    }
}
