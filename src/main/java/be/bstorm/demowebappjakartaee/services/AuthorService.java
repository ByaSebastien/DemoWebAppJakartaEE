package be.bstorm.demowebappjakartaee.services;

import be.bstorm.demowebappjakartaee.entities.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();
    Author findById(Long id);
}
