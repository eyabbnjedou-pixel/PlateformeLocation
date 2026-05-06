package controller.user;

import java.io.IOException;
import java.util.List;

import dao.AvisBienDao;
import dao.BienDao;
import dao.impl.AvisBienDaoImpl;
import dao.impl.BienDaoImpl;
import model.AvisBien;
import model.Bien;
import model.Utilisateur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/user/bien")
public class UserBienDetailsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BienDao bienDao = new BienDaoImpl();

    private AvisBienDao avisBienDao = new AvisBienDaoImpl();

    private Utilisateur getUser(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        return (session != null)
                ? (Utilisateur) session.getAttribute("userSession")
                : null;
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");

        if (idStr == null || idStr.isEmpty()) {

            response.sendRedirect(
                    request.getContextPath() + "/user/home"
            );

            return;
        }

        try {

            Long bienId = Long.parseLong(idStr);

            Bien bien = bienDao.findById(bienId);

            if (bien == null) {

                response.sendRedirect(
                        request.getContextPath() + "/user/home"
                );

                return;
            }

            List<AvisBien> avisList =
                    avisBienDao.findByBien(bienId);

            Double moyenne =
                    avisBienDao.getAverageNoteByBien(bienId);

            Utilisateur user = getUser(request);

            AvisBien avisUser = null;

            if (user != null) {

                avisUser =
                        avisBienDao.findByBienAndUtilisateur(
                                bienId,
                                user.getId()
                        );
            }

            request.setAttribute("bien", bien);

            request.setAttribute("avisList", avisList);

            request.setAttribute("moyenne", moyenne);

            request.setAttribute("avisUser", avisUser);

            request.getRequestDispatcher(
                    "/WEB-INF/views/user/Bien/details.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        Utilisateur user = getUser(request);

        if (user == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );

            return;
        }

        String action = request.getParameter("action");

        if (action == null) action = "saveAvis";

        try {

            if ("saveAvis".equals(action)) {

                saveAvis(request, response, user);

            } else if ("deleteAvis".equals(action)) {

                deleteAvis(request, response, user);

            } else {

                response.sendRedirect(
                        request.getContextPath() + "/user/home"
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            throw new ServletException(e);
        }
    }

    private void saveAvis(HttpServletRequest request,
                          HttpServletResponse response,
                          Utilisateur user)
            throws Exception {

        String bienIdStr =
                request.getParameter("bienId");

        String noteStr =
                request.getParameter("note");

        String commentaire =
                request.getParameter("commentaire");

        Long bienId =
                Long.parseLong(bienIdStr);

        String error = null;

        int note = 0;

        try {

            note = Integer.parseInt(noteStr);

            if (note < 1 || note > 5) {

                error =
                        "La note doit être entre 1 et 5";
            }

        } catch (NumberFormatException e) {

            error = "Note invalide";
        }

        AvisBien avis =
                avisBienDao.findByBienAndUtilisateur(
                        bienId,
                        user.getId()
                );

        if (avis == null) {

            avis = new AvisBien();

            Bien bien = new Bien();
            bien.setId(bienId);
            avis.setBien(bien);
            avis.setUtilisateur(user);
        }

        avis.setNote(note);

        avis.setCommentaire(commentaire);

        if (error != null) {

            request.setAttribute("error", error);

            Bien bien =
                    bienDao.findById(bienId);

            request.setAttribute("bien", bien);

            request.setAttribute(
                    "avisUser",
                    avis
            );

            request.setAttribute(
                    "avisList",
                    avisBienDao.findByBien(bienId)
            );

            request.setAttribute(
                    "moyenne",
                    avisBienDao.getAverageNoteByBien(
                            bienId
                    )
            );

            request.getRequestDispatcher(
                    "/WEB-INF/views/user/Bien/details.jsp"
            ).forward(request, response);

            return;
        }

        avisBienDao.save(avis);

        response.sendRedirect(
                request.getContextPath()
                        + "/user/bien?id="
                        + bienId
        );
    }

    private void deleteAvis(HttpServletRequest request,
                            HttpServletResponse response,
                            Utilisateur user)
            throws Exception {

        String avisIdStr =
                request.getParameter("avisId");

        String bienIdStr =
                request.getParameter("bienId");

        if (avisIdStr != null
                && !avisIdStr.isEmpty()) {

            Long avisId =
                    Long.parseLong(avisIdStr);

            avisBienDao.delete(avisId);
        }

        response.sendRedirect(
                request.getContextPath()
                        + "/user/bien?id="
                        + bienIdStr
        );
    }
}