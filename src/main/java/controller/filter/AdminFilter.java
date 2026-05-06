package controller.filter;

import java.io.IOException;

import model.Utilisateur;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // rien
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        Utilisateur user = (session != null) ? (Utilisateur) session.getAttribute("userSession") : null;

        if (user == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String path = req.getRequestURI().substring(req.getContextPath().length());

        if ("AGENT".equals(user.getRole())) {
            // L'agent n'a pas accès à users, dashboard et stats de l'admin
            if (path.startsWith("/admin/users") || path.startsWith("/admin/dashboard") || path.startsWith("/admin/stats")) {
                res.sendRedirect(req.getContextPath() + "/agent/dashboard");
                return;
            }
            // Sinon on laisse passer l'agent pour biens, reservations, etc.
        } else if (!"ADMIN".equals(user.getRole())) {
            // Ni admin ni agent sur une route admin
            res.sendRedirect(req.getContextPath() + "/user/home");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // rien
    }
}