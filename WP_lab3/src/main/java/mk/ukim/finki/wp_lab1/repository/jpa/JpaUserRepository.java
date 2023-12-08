package mk.ukim.finki.wp_lab1.repository.jpa;

import mk.ukim.finki.wp_lab1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndPassword(String username, String password);
}
