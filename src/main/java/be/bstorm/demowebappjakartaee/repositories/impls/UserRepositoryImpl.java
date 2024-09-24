package be.bstorm.demowebappjakartaee.repositories.impls;

import be.bstorm.demowebappjakartaee.entities.User;
import be.bstorm.demowebappjakartaee.repositories.UserRepository;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.Optional;

@SessionScoped
public class UserRepositoryImpl extends BaseRepositoryImpl<User,Long> implements UserRepository, Serializable {

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try (EntityManager em = emf.createEntityManager()){

            TypedQuery<User> query = em.createQuery("select u from User u where u.username = :username", User.class);
            query.setParameter("username", username);
            User user = query.getSingleResult();
            return Optional.of(user);
        }
    }

    @Override
    public Boolean existsByUsername(String username) {
        try (EntityManager em = emf.createEntityManager()){
            TypedQuery<Boolean> query = em.createQuery("select count(u) > 0 from User u where u.username = :username", Boolean.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        }
    }
}
