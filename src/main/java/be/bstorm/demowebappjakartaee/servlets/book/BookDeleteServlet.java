package be.bstorm.demowebappjakartaee.servlets.book;

import be.bstorm.demowebappjakartaee.services.BookService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/book/delete")
public class BookDeleteServlet extends HttpServlet {

    @Inject
    private BookService bookService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Récupérer l'ISBN du livre à supprimer à partir des paramètres de la requête
        String isbn = req.getParameter("isbn");
        if (isbn != null && !isbn.isEmpty()) {
            // Suppression du livre dans le service
            bookService.delete(isbn);
        }

        // Rediriger vers la page d'accueil ou la liste des livres
        resp.sendRedirect(req.getContextPath() + "/books");
    }
}
