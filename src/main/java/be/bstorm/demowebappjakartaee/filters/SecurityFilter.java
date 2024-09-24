package be.bstorm.demowebappjakartaee.filters;

import be.bstorm.demowebappjakartaee.entities.User;
import be.bstorm.demowebappjakartaee.services.security.AuthService;
import jakarta.inject.Inject;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class SecurityFilter implements Filter {

    @Inject
    private AuthService authService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);
        User user = null;

        // Vérifie si un utilisateur est connecté
        if (session != null && session.getAttribute("user") != null) {
            User u = (User) session.getAttribute("user");
            user = authService.loadUserByUsername(u.getUsername()); // Charger l'utilisateur depuis la base
        }

        // Gérer les routes pour les utilisateurs non-connectés
        if (isNonAuthenticatedRoute(uri)) {
            if (user == null) {
                chain.doFilter(request, response); // Accessible uniquement aux non-connectés
            } else {
                resp.sendRedirect(req.getContextPath() + "/"); // Rediriger vers une page appropriée
            }
            return;
        }

        // Gérer les routes pour les administrateurs
        if (isAdminRoute(uri)) {
            if (user != null && "ADMIN".equals(user.getRole())) {
                chain.doFilter(request, response); // Admin autorisé
            } else {
                req.getRequestDispatcher("/WEB-INF/pages/accessDenied.jsp").forward(req, resp); // Refus d'accès
            }
            return;
        }

        // Gérer les routes pour les utilisateurs connectés
        if (isAuthenticatedRoute(uri)) {
            if (user != null) {
                chain.doFilter(request, response); // Utilisateur connecté autorisé
            } else {
                resp.sendRedirect(req.getContextPath() + "/login"); // Redirige vers login si non connecté
            }
            return;
        }

        // Si aucune condition ne correspond, rediriger vers la page de login
        chain.doFilter(request, response);
    }

    // Pour utiliser les annotations sur servlet (Impossible avec tomcat)
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;
//
//        HttpSession session = req.getSession(false);
//        if (session != null && session.getAttribute("user") != null) {
//            // Récupérer le rôle de la session (par exemple, "ADMIN" ou "USER")
//            String username = (String) session.getAttribute("user");
//
//            User user = authService.loadUserByUsername(username);
//
//            // Injecter le rôle et l'utilisateur dans le SecurityContext via un HttpServletRequestWrapper
//            HttpServletRequestWrapper wrappedRequest = new HttpServletRequestWrapper(req) {
//                @Override
//                public Principal getUserPrincipal() {
//                    return user::getUsername; // Retourne l'utilisateur authentifié
//                }
//
//                @Override
//                public boolean isUserInRole(String roleName) {
//                    return roleName.equals(user.getRole()); // Vérifie le rôle de l'utilisateur
//                }
//            };
//            // Continuer avec la requête modifiée
//            chain.doFilter(wrappedRequest, response);
//        } else {
//            HttpServletRequestWrapper wrappedRequest = new HttpServletRequestWrapper(req) {
//                @Override
//                public Principal getUserPrincipal() {
//                    return null; // Retourne l'utilisateur authentifié
//                }
//
//                @Override
//                public boolean isUserInRole(String roleName) {
//                    return roleName.equals("ANONYMOUS"); // Vérifie le rôle de l'utilisateur
//                }
//            };
//            // Continuer avec la requête modifiée
//            chain.doFilter(wrappedRequest, response);
//        }
//    }

    // Exemple de méthode pour déterminer si une route est accessible uniquement aux administrateurs
    private boolean isAdminRoute(String uri) {
        return uri.startsWith("/myapp/admin");
    }

    // Exemple de méthode pour déterminer si une route est accessible uniquement aux utilisateurs connectés
    private boolean isAuthenticatedRoute(String uri) {
        return uri.startsWith("/myapp/user") || uri.startsWith("/myapp/logout");
    }

    // Exemple de méthode pour déterminer si une route est accessible uniquement aux non-connectés
    private boolean isNonAuthenticatedRoute(String uri) {
        return uri.startsWith("/myapp/login") || uri.startsWith("/myapp/register");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialisation si nécessaire
    }

    @Override
    public void destroy() {
        // Libérer les ressources si nécessaire
    }
}
