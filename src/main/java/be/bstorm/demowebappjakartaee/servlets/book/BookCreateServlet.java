package be.bstorm.demowebappjakartaee.servlets.book;

import be.bstorm.demowebappjakartaee.entities.Author;
import be.bstorm.demowebappjakartaee.entities.Book;
import be.bstorm.demowebappjakartaee.models.author.AuthorDTO;
import be.bstorm.demowebappjakartaee.services.AuthorService;
import be.bstorm.demowebappjakartaee.services.BookService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jakarta.annotation.security.RolesAllowed;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/admin/book/create")
//@RolesAllowed({"ADMIN"}) Impossible avec tomcat
public class BookCreateServlet extends HttpServlet {

    @Inject
    private BookService bookService;
    @Inject
    private AuthorService authorService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Récupérer la liste des auteurs existants
        List<AuthorDTO> authors = authorService.findAll()
                .stream()
                .map(AuthorDTO::fromEntity)
                .collect(Collectors.toList());

        // Ajouter la liste des auteurs à la requête pour être utilisée dans le JSP
        req.setAttribute("authors", authors);

        // Transférer la requête vers la JSP pour afficher le formulaire de création de livre
        req.getRequestDispatcher("/WEB-INF/pages/book/createBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Récupération des paramètres du livre
        String title = req.getParameter("title");
        String isbn = req.getParameter("isbn");
        String description = req.getParameter("description");
        LocalDate publicationDate = LocalDate.parse(req.getParameter("publicationDate"));

        // Détermination de l'auteur (existant ou nouveau)
        Author author = null;

        String authorIdParam = req.getParameter("authorId"); // Auteur existant

        // Si un auteur existant est sélectionné
        if (authorIdParam != null && !authorIdParam.isEmpty()) {
            Long authorId = Long.parseLong(authorIdParam);
            author = authorService.findById(authorId);
        } else {
            String newAuthorFirstName = req.getParameter("newAuthorFirstName"); // Nouvel auteur
            String newAuthorLastName = req.getParameter("newAuthorLastName");
            String newAuthorPseudo = req.getParameter("newAuthorPseudo");
            String newAuthorBirthDateParam = req.getParameter("newAuthorBirthDate");
            LocalDate newAuthorBirthDate = LocalDate.parse(newAuthorBirthDateParam);
            author = new Author(newAuthorFirstName, newAuthorLastName, newAuthorPseudo, newAuthorBirthDate);
        }

        // Création du nouveau livre avec l'auteur sélectionné ou créé
        Book book = new Book(isbn, title, description, publicationDate, author);
        bookService.save(book);

        // Rediriger vers la liste des livres après la création
        resp.sendRedirect(req.getContextPath() + "/");
    }
}

