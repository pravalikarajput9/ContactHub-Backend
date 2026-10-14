package app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import app.models.User;

public interface UserRepo extends JpaRepository<User, String> {
}        