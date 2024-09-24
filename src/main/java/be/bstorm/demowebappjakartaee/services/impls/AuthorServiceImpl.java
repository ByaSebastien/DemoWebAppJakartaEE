package be.bstorm.demowebappjakartaee.services.impls;

import be.bstorm.demowebappjakartaee.entities.Author;
import be.bstorm.demowebappjakartaee.repositories.AuthorRepository;
import be.bstorm.demowebappjakartaee.services.AuthorService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@SessionScoped
public class AuthorServiceImpl implements AuthorService, Serializable {

    @Inject
    private AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow();
    }
}
