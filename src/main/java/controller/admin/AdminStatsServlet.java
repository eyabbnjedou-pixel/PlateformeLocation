package controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import dao.StatsDao;
import dao.impl.StatsDaoImpl;
import model.BienStats;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/stats")
public class AdminStatsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private StatsDao statsDao = new StatsDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            long totalReservations = statsDao.countTotalReservations();
            Map<String, Long> statsByStatus = statsDao.countReservationsByStatus();
            List<BienStats> topBiens = statsDao.topbiens(5);

            long pending = statsByStatus.getOrDefault("EN_ATTENTE", 0L);
            long confirmed = statsByStatus.getOrDefault("VALIDEE", 0L); // ATTENTION: statut = VALIDEE

            request.setAttribute("totalReservations", totalReservations);
            request.setAttribute("statsByStatus", statsByStatus);
            request.setAttribute("topSalles", topBiens);

            // Ajout pour les cards "En attente" et "Confirmées"
            request.setAttribute("pendingReservations", pending);
            request.setAttribute("confirmedReservations", confirmed);

            request.getRequestDispatcher("/WEB-INF/views/admin/stats.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}