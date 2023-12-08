package mk.ukim.finki.wp_lab1.service;

import mk.ukim.finki.wp_lab1.model.User;
import mk.ukim.finki.wp_lab1.model.UserFullName;

import java.util.Optional;

public interface AuthService {
    Optional<User> login(String username, String password);
    User register(String username, String password, String repeatPassword, UserFullName userFullName);
    void logout();
}
