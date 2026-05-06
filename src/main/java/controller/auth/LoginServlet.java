package controller.auth;

import java.io.IOException;
import java.util.Objects;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.Utilisateur;
import service.UtilisateurService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UtilisateurService userService = new UtilisateurService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp")
               .forward(request, response);
    } 
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("motDePasse");

        // 🔐 validation
        if (login == null || password == null ||
            login.trim().isEmpty() || password.trim().isEmpty()) {

            request.setAttribute("error", "Veuillez remplir tous les champs");
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
            return;
        }

        Utilisateur user = userService.login(login.trim(), password.trim());

        if (user == null) {
            request.setAttribute("error", "Login ou mot de passe incorrect");
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
            return;
        }

        // 🔐 session sécurisée
        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("userSession", user);
        session.setMaxInactiveInterval(30 * 60); // 30 minutes

     // headers (اختياري)
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        
        // redirect حسب role
        if ("ADMIN".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        } else if ("AGENT".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/agent/dashboard");
        } else {
            response.sendRedirect(request.getContextPath() + "/user/home");
        }
        return;
    }

	public UtilisateurService getUserService() {
		return userService;
	}

	public void setUserService(UtilisateurService userService) {
		this.userService = userService;
	}


	@Override
	public int hashCode() {
		return Objects.hash(userService);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginServlet other = (LoginServlet) obj;
		return Objects.equals(userService, other.userService);
	}
}