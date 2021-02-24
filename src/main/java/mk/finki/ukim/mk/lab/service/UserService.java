package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.enums.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    boolean checkIfExists(String name, String password);
    Optional<User> findByUsername(String username);
    User registerUser(String username, String password, String repeatPassword, Role role);
}
