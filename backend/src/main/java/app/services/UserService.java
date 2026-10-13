package app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.models.User;
import app.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    UserRepo repo;

    public String register(User user) {
    repo.save(user);
    return "Registration Successful";
}

    public String login(User user) {
        User dbUser = repo.findById(user.getEmail()).orElse(null);

        if (dbUser != null && dbUser.getPassword().equals(user.getPassword())) {
            return "Login Successful";
        }
        return "Invalid Email or Password";
    }
}  