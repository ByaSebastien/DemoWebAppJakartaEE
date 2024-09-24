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

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/admin/book/update")
public class BookUpdateServlet extends HttpServlet {

    @Inject
    private BookService bookService;

    @Inject
    private AuthorService authorService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Récupérer l'ISBN du livre à mettre à jour
        String isbn = req.getParameter("isbn");
        if (isbn == null || isbn.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ISBN manquant");
            return;
        }

        // Récupérer le livre à partir de l'ISBN
        Book book = bookService.findById(isbn);
        if (book == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Livre non trouvé");
            return;
        }

        // Récupérer tous les auteurs pour remplir la liste déroulante
        List<AuthorDTO> authors = authorService.findAll()
                .stream()
                .map(AuthorDTO::fromEntity)
                .collect(Collectors.toList());

        // Ajouter les données du livre et la liste des auteurs à la requête
        req.setAttribute("book", book);
        req.setAttribute("authors", authors);

        // Transférer vers la page de mise à jour
        req.getRequestDispatcher("/WEB-INF/pages/book/updateBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Récupérer les données du formulaire
        String isbn = req.getParameter("isbn");
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        LocalDate publicationDate = LocalDate.parse(req.getParameter("publicationDate"));

        // Détermination de l'auteur
        String authorIdParam = req.getParameter("authorId");
        Author author = null;

        if (authorIdParam != null && !authorIdParam.isEmpty()) {
            Long authorId = Long.parseLong(authorIdParam);
            author = authorService.findById(authorId);
        } else {
            // Création d'un nouvel auteur
            String newAuthorFirstName = req.getParameter("newAuthorFirstName");
            String newAuthorLastName = req.getParameter("newAuthorLastName");
            String newAuthorPseudo = req.getParameter("newAuthorPseudo");
            LocalDate newAuthorBirthDate = LocalDate.parse(req.getParameter("newAuthorBirthDate"));
            author = new Author(newAuthorFirstName, newAuthorLastName, newAuthorPseudo, newAuthorBirthDate);
        }

        // Sauvegarder les modifications
        Book book = new Book(isbn, title, description, publicationDate, author);
        bookService.update(book);

        // Rediriger vers la liste des livres après la création
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
