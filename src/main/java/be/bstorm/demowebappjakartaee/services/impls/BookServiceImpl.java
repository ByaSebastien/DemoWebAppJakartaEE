package be.bstorm.demowebappjakartaee.services.impls;

import be.bstorm.demowebappjakartaee.entities.Book;
import be.bstorm.demowebappjakartaee.repositories.BookRepository;
import be.bstorm.demowebappjakartaee.services.BookService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@SessionScoped
public class BookServiceImpl implements BookService, Serializable {

    @Inject
    private BookRepository bookRepository;

    public BookServiceImpl() {}

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book findById(String isbn) {
        return bookRepository.findById(isbn).orElseThrow();
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void update(Book b) {
        Book book = bookRepository.findById(b.getIsbn()).orElseThrow();
        book.setTitle(b.getTitle());
        book.setAuthor(b.getAuthor());
        book.setDescription(b.getDescription());
        book.setPublicationDate(b.getPublicationDate());
        bookRepository.update(book);
    }

    @Override
    public void delete(String isbn) {
        bookRepository.delete(isbn);
    }
}
