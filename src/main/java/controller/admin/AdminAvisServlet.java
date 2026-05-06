package controller.admin;

import java.io.IOException;
import java.util.List;

import dao.AvisBienDao;
import dao.impl.AvisBienDaoImpl;
import model.AvisBien;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/avis")
public class AdminAvisServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private AvisBienDao avisBienDao = new AvisBienDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            if ("delete".equals(action)) {
                deleteAvis(request, response);
            } else {
                listAvis(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    private void listAvis(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        List<AvisBien> avis = avisBienDao.findAll();
        request.setAttribute("avis", avis);
        request.getRequestDispatcher("/WEB-INF/views/admin/avis/list.jsp")
               .forward(request, response);
    }

    private void deleteAvis(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            Long id = Long.parseLong(idStr);
            avisBienDao.delete(id);
        }
        response.sendRedirect(request.getContextPath() + "/admin/avis");
    }
}