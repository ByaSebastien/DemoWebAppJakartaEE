package be.bstorm.demowebappjakartaee.servlets.book;

import be.bstorm.demowebappjakartaee.models.book.BookDTO;
import be.bstorm.demowebappjakartaee.services.BookService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/book/details")
public class BookDetailsServlet extends HttpServlet {

    @Inject
    private BookService bookService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String isbn = req.getParameter("isbn");
        BookDTO book = BookDTO.fromEntity(bookService.findById(isbn));

        req.setAttribute("book", book);
        req.getRequestDispatcher("/WEB-INF/pages/book/bookDetails.jsp").forward(req, resp);
    }
}

