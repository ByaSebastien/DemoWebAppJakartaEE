package be.bstorm.demowebappjakartaee.servlets.book;

import be.bstorm.demowebappjakartaee.models.book.BookShortDTO;
import be.bstorm.demowebappjakartaee.services.BookService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "BookIndexServlet", urlPatterns = "/")  // Servlet mappé à la racine
public class BookIndexServlet extends HttpServlet {

    @Inject
    private BookService bookService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Récupérer tous les livres depuis le service
        List<BookShortDTO> books = bookService.findAll()
                .stream()
                .map(BookShortDTO::fromEntity)
                .collect(Collectors.toList());

        // Ajouter les livres à la requête
        req.setAttribute("books", books);

        // Rediriger vers index.jsp pour affichage
        req.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(req, resp);
    }
}
