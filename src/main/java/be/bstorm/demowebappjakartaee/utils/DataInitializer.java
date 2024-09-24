package be.bstorm.demowebappjakartaee.utils;

import be.bstorm.demowebappjakartaee.entities.Author;
import be.bstorm.demowebappjakartaee.entities.Book;
import be.bstorm.demowebappjakartaee.entities.User;
import be.bstorm.demowebappjakartaee.repositories.UserRepository;
import be.bstorm.demowebappjakartaee.repositories.impls.BookRepositoryImpl;
import be.bstorm.demowebappjakartaee.repositories.impls.UserRepositoryImpl;
import be.bstorm.demowebappjakartaee.services.BookService;
import be.bstorm.demowebappjakartaee.services.impls.BookServiceImpl;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDate;

@WebListener
public class DataInitializer implements ServletContextListener {

    private final BookService bookService;
    private final UserRepository userRepository;

    public DataInitializer() {
        bookService = new BookServiceImpl(new BookRepositoryImpl());
        userRepository = new UserRepositoryImpl();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Liste de 10 livres réels avec leurs auteurs
        Author author1 = new Author("Victor", "Hugo", "Hugo", LocalDate.of(1802, 2, 26));
        Book book1 = new Book("9782070408330", "Les Misérables", "Un roman sur la justice, la morale et la rédemption dans la France du XIXe siècle.", LocalDate.of(1862, 4, 3), author1);

        Author author2 = new Author("Gustave", "Flaubert", "Flaubert", LocalDate.of(1821, 12, 12));
        Book book2 = new Book("9782070413112", "Madame Bovary", "Une critique de la bourgeoisie et des contraintes de la société.", LocalDate.of(1857, 12, 12), author2);

        Author author3 = new Author("Marcel", "Proust", "Proust", LocalDate.of(1871, 7, 10));
        Book book3 = new Book("9782070118543", "À la recherche du temps perdu", "Une réflexion sur la mémoire, le temps et l'art.", LocalDate.of(1913, 11, 14), author3);

        Author author4 = new Author("Albert", "Camus", "Camus", LocalDate.of(1913, 11, 7));
        Book book4 = new Book("9782070360024", "L'Étranger", "Une histoire existentialiste sur l'absurde.", LocalDate.of(1942, 6, 19), author4);

        Author author5 = new Author("Antoine", "de Saint-Exupéry", "Saint-Exupéry", LocalDate.of(1900, 6, 29));
        Book book5 = new Book("9782070612758", "Le Petit Prince", "Un conte philosophique sur un jeune prince explorant des planètes.", LocalDate.of(1943, 4, 6), author5);

        Author author6 = new Author("Émile", "Zola", "Zola", LocalDate.of(1840, 4, 2));
        Book book6 = new Book("9782070442787", "Germinal", "Un portrait des luttes ouvrières et de l'injustice sociale.", LocalDate.of(1885, 3, 3), author6);

        Author author7 = new Author("Honoré", "de Balzac", "Balzac", LocalDate.of(1799, 5, 20));
        Book book7 = new Book("9782070361453", "Le Père Goriot", "Une étude de la cupidité et des relations familiales.", LocalDate.of(1835, 1, 1), author7);

        Author author8 = new Author("Jules", "Verne", "Verne", LocalDate.of(1828, 2, 8));
        Book book8 = new Book("9782070623674", "Vingt Mille Lieues sous les mers", "Une aventure de science-fiction sous la mer.", LocalDate.of(1870, 6, 20), author8);

        Author author9 = new Author("Molière", "Jean-Baptiste Poquelin", "Molière", LocalDate.of(1622, 1, 15));
        Book book9 = new Book("9782070360678", "Le Misanthrope", "Une comédie sur l'hypocrisie et la société.", LocalDate.of(1666, 6, 4), author9);

        Author author10 = new Author("François", "Rabelais", "Rabelais", LocalDate.of(1494, 2, 4));
        Book book10 = new Book("9782070400823", "Gargantua", "Une satire sur l'éducation, la politique et la religion.", LocalDate.of(1534, 11, 11), author10);

        // Persister les livres (et leurs auteurs en Cascade.PERSIST)
        bookService.save(book1);
        bookService.save(book2);
        bookService.save(book3);
        bookService.save(book4);
        bookService.save(book5);
        bookService.save(book6);
        bookService.save(book7);
        bookService.save(book8);
        bookService.save(book9);
        bookService.save(book10);

        System.out.println("10 books and their authors have been initialized.");

        User admin = new User("Admin", BCrypt.hashpw("Test1234=",BCrypt.gensalt()),"ADMIN");
        User user = new User("User", BCrypt.hashpw("Test1234=",BCrypt.gensalt()),"USER");

        userRepository.save(admin);
        userRepository.save(user);

        System.out.println("User has been initialized.");
    }
}
