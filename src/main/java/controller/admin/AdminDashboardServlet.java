package controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import dao.BienDao;
import dao.UtilisateurDao;
import dao.StatsDao;
import dao.AvisBienDao;
import dao.ReservationDao;
import dao.impl.BienDaoImpl;
import dao.impl.UtilisateurDaoImpl;
import dao.impl.StatsDaoImpl;
import dao.impl.AvisBienDaoImpl;
import dao.impl.ReservationDaoImpl;
import model.Bien;
import model.Utilisateur;
import model.Reservation;
import model.AvisBien;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BienDao bienDao = new BienDaoImpl();
    private UtilisateurDao utilisateurDao = new UtilisateurDaoImpl();
    private StatsDao statsDao = new StatsDaoImpl();
    private AvisBienDao avisBienDao = new AvisBienDaoImpl();
    private ReservationDao reservationDao = new ReservationDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // ====== Totaux simples ======
            List<Bien> biens = bienDao.findAll();
            long totalBiens = biens.size();

            List<Utilisateur> utilisateurs = utilisateurDao.findAll();
            long totalUsers = utilisateurs.size();

            List<AvisBien> avis = avisBienDao.findAll();
            long totalAvis = avis.size();

            // ====== Stats par statut ======
            // Map<String, Long> avec clés : EN_ATTENTE, VALIDEE, REFUSEE, ANNULEE
            Map<String, Long> statsByStatus = statsDao.countReservationsByStatus();
            long pending = statsByStatus.getOrDefault("EN_ATTENTE", 0L);
            long confirmed = statsByStatus.getOrDefault("CONFIRMEE", 0L) + statsByStatus.getOrDefault("PAYEE", 0L);

            // ====== Réservations récentes ======
            // findAll() est déjà trié par date_heure_debut DESC dans ton DAO
            List<Reservation> allReservations = reservationDao.findAll();
            List<Reservation> recentReservations = allReservations;
            if (recentReservations.size() > 5) {
                recentReservations = recentReservations.subList(0, 5);
            }

            // ====== Attributs pour la JSP ======
            request.setAttribute("totalBiens", totalBiens);
            request.setAttribute("totalUsers", totalUsers);
            request.setAttribute("totalAvis", totalAvis);
            request.setAttribute("pendingReservations", pending);
            request.setAttribute("confirmedReservations", confirmed);
            request.setAttribute("recentReservations", recentReservations);

            request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}