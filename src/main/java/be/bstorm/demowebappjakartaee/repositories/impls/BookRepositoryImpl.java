package be.bstorm.demowebappjakartaee.repositories.impls;

import be.bstorm.demowebappjakartaee.entities.Book;
import be.bstorm.demowebappjakartaee.repositories.BookRepository;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;

import java.io.Serializable;
import java.util.List;

@SessionScoped
public class BookRepositoryImpl extends BaseRepositoryImpl<Book,String> implements BookRepository, Serializable {

    public BookRepositoryImpl() {
        super(Book.class);
    }

    @Override
    public List<Book> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("select b from Book b join b.author", Book.class).getResultList();
        }
    }
}
