package be.bstorm.demowebappjakartaee.repositories;

import be.bstorm.demowebappjakartaee.entities.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}
