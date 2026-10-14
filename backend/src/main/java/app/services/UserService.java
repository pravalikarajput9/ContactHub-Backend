package app.services;

import app.models.User;
import app.repo.UserRepo;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    public String register(User user) {
        if (user == null
                || user.getEmail() == null
                || user.getEmail().isBlank()
                || user.getPassword() == null
                || user.getPassword().isBlank()) {
            return "Email and password are required";
        }

        String email = user.getEmail().trim().toLowerCase();

        if (repo.existsById(email)) {
            return "Email is already registered";
        }

        user.setEmail(email);
        repo.save(user);

        return "Registration Successful";
    }

    public String login(User user) {
        if (user == null
                || user.getEmail() == null
                || user.getPassword() == null) {
            return "Email and password are required";
        }

        String email = user.getEmail().trim().toLowerCase();

        User dbUser = repo.findById(email).orElse(null);

        if (dbUser != null
                && dbUser.getPassword() != null
                && dbUser.getPassword().equals(user.getPassword())) {
            return "Login Successful";
        }

        return "Invalid Email or Password";
    }
}
