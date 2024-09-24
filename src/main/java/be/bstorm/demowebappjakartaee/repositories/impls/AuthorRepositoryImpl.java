package be.bstorm.demowebappjakartaee.repositories.impls;

import be.bstorm.demowebappjakartaee.entities.Author;
import be.bstorm.demowebappjakartaee.repositories.AuthorRepository;
import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;

@SessionScoped
public class AuthorRepositoryImpl extends BaseRepositoryImpl<Author,Long> implements AuthorRepository, Serializable {

    public AuthorRepositoryImpl() {
        super(Author.class);
    }
}
