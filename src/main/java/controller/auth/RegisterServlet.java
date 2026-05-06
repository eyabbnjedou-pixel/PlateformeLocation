package controller.auth;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.Utilisateur;
import service.UtilisateurService;
import util.VerifyRecaptcha;

import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UtilisateurService userService = new UtilisateurService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ✅ reCAPTCHA
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");

        if (gRecaptchaResponse == null || gRecaptchaResponse.isEmpty()) {
            request.setAttribute("error", "Veuillez vérifier le reCAPTCHA !");
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
            return;
        }

        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);

        if (!verify) {
            request.setAttribute("error", "Échec du reCAPTCHA !");
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
            return;
        }

        // ✅ récupération
        String login = request.getParameter("login");
        String password = request.getParameter("motDePasse");
        String nom = request.getParameter("nomComplet");
        String role = request.getParameter("role");

        // ✅ validation
        if (login == null || password == null || nom == null || role == null ||
            login.trim().isEmpty() || password.trim().isEmpty() || nom.trim().isEmpty() || role.trim().isEmpty()) {

            request.setAttribute("error", "Tous les champs sont obligatoires");
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
            return;
        }

        // ✅ création user
        Utilisateur user = new Utilisateur();
        user.setLogin(login.trim());
        String hashed = BCrypt.hashpw(password.trim(), BCrypt.gensalt());
        user.setMotDePasse(hashed);
        user.setNomComplet(nom.trim());
        user.setRole(role.trim());
        user.setActif(true);

        try {
            userService.register(user);
            response.sendRedirect(request.getContextPath() + "/login");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
        }
    }
}