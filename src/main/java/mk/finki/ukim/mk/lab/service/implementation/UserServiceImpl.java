package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.enums.Role;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.mk.lab.model.exceptions.PasswordsDontMatchException;
import mk.finki.ukim.mk.lab.model.exceptions.UserAlreadyExistsException;
import mk.finki.ukim.mk.lab.repository.InDatabase.UserRepository;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean checkIfExists(String name, String password)  {
        if(userRepository.findByUsernameAndPassword(name, password).isPresent())
            return true;

        return false;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public User registerUser(String username, String password, String repeatPassword, Role role) throws UserAlreadyExistsException, InvalidArgumentsException {
        if(username == null || username.isEmpty() || password == null || password.isEmpty() ||
                repeatPassword == null || repeatPassword.isEmpty())
            throw new InvalidArgumentsException();
        if(!password.equals(repeatPassword))
            throw new PasswordsDontMatchException(password, repeatPassword);
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UserAlreadyExistsException(username);

        String encryptedPassword = this.passwordEncoder.encode(password);

        User user = new User(username, encryptedPassword, role);
        this.userRepository.save(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException(s));
    }
}

