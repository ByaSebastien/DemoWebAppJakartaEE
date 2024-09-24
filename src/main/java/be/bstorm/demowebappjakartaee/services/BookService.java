package be.bstorm.demowebappjakartaee.services;

import be.bstorm.demowebappjakartaee.entities.Book;

import java.util.List;

public interface BookService {

    Book save(Book book);
    Book findById(String isbn);
    List<Book> findAll();
    void update(Book book);
    void delete(String isbn);
}
