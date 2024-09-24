package be.bstorm.demowebappjakartaee.services.security;

import be.bstorm.demowebappjakartaee.entities.User;
import be.bstorm.demowebappjakartaee.repositories.UserRepository;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import org.mindrot.jbcrypt.BCrypt;

import java.io.Serializable;

@SessionScoped
public class AuthServiceImpl implements AuthService, Serializable {

    @Inject
    private UserRepository userRepository;

    @Override
    public void register(String username, String password) {
        if( userRepository.existsByUsername(username) ) {
            throw new RuntimeException("User with username " + username + " already exists");
        }
        User user = new User(username, BCrypt.hashpw(password,BCrypt.gensalt()),"USER");
        userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow();
        if( !BCrypt.checkpw(password,user.getPassword()) ) {
            throw new RuntimeException("Incorrect password");
        }
        return user;
    }

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }
}
