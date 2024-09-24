package be.bstorm.demowebappjakartaee.services.security;

import be.bstorm.demowebappjakartaee.entities.User;

public interface AuthService {

    void register(String username, String password);
    User login(String username, String password);
    User loadUserByUsername(String username);
}
