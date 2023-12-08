package mk.ukim.finki.wp_lab1.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp_lab1.model.User;
import mk.ukim.finki.wp_lab1.model.UserFullName;
import mk.ukim.finki.wp_lab1.repository.jpa.JpaUserRepository;
import mk.ukim.finki.wp_lab1.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JpaUserRepository userRepository;
    @Override
    public Optional<User> login(String username, String password) {
        if (username==null || password == null || username.isEmpty() || password.isEmpty()) {
            return null;
        }

        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public User register(String username, String password, String repeatPassword, UserFullName fullName) {
        if (username==null ||
                username.isEmpty()  ||
                password==null ||
                password.isEmpty() ||
                fullName.getName() == null ||
                fullName.getName().isEmpty() ||
                fullName.getSurname() == null ||
                fullName.getSurname().isEmpty()) {
            return null;
        }

        if (!password.equals(repeatPassword)) {
            return null;
        }

        User user = new User(username,password, fullName);
        return userRepository.save(user);
    }

    @Override
    public void logout() {

    }
}
