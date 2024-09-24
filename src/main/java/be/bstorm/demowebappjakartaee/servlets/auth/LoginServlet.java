package be.bstorm.demowebappjakartaee.servlets.auth;

import be.bstorm.demowebappjakartaee.entities.User;
import be.bstorm.demowebappjakartaee.services.security.AuthService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Inject
    private AuthService authService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {

            User user = authService.login(username, password);
            req.getSession(true).setAttribute("user", user);

        } catch (Exception e) {

        }
        resp.sendRedirect(req.getRequestURI() + "/");
    }
}
