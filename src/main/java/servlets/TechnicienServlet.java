package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import beans.Technicien;
import dao.DAOFactory;
import dao.daoTechnicien.TechnicienDAO;

/**
 * Servlet implementation class TechnicienServlet
 */
@WebServlet("/TechnicienServlet")
public class TechnicienServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TechnicienDAO technicienDao;

    @Override
    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.technicienDao = daoFactory.getTechnicientDAO();
    }

    public TechnicienServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null) {
                listTechniciens(request, response);
            } else {
                switch (action) {
                    case "ajouter":
                        showNewForm(request, response);
                        break;
                    case "modifier":
                        showEditForm(request, response);
                        break;
                    case "delete":
                        deleteTechnicien(request, response);
                        break;
                    default:
                        listTechniciens(request, response);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Une erreur s'est produite : " + e.getMessage());
        }
    }

    private void listTechniciens(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Technicien> techniciens = technicienDao.getAllTechniciens();
            request.setAttribute("techniciens", techniciens);
            request.getRequestDispatcher("/TechnicientJSP/technicient.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des techniciens.");
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/TechnicienJSP/technicienAdd.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID du technicien manquant.");
                return;
            }
            int id = Integer.parseInt(idParam);
            Technicien technicien = technicienDao.getTechnicienById(id);
            if (technicien == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Technicien non trouvé.");
                return;
            }
            request.setAttribute("technicien", technicien);
            request.getRequestDispatcher("/TechnicienJSP/technicienUpdate.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID du technicien invalide.");
        }
    }

    private void deleteTechnicien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID du technicien manquant.");
                return;
            }
            int id = Integer.parseInt(idParam);
            technicienDao.deleteTechnicien(id);
            response.sendRedirect(request.getContextPath() + "/TechnicienServlet");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID du technicien invalide.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la suppression du technicien.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("MettreAjour")) {
            updateDisponibilite(request, response);
        }
    }

    private void updateDisponibilite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String disponibiliteParam = request.getParameter("disponibilite");
            String technicienIdStr = request.getParameter("id");

            if (technicienIdStr == null || technicienIdStr.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID du technicien est obligatoire.");
                return;
            }

            boolean disponibilite = disponibiliteParam != null && disponibiliteParam.equalsIgnoreCase("true");

            int technicienId;
            try {
                technicienId = Integer.parseInt(technicienIdStr);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID du technicien invalide.");
                return;
            }

            boolean isUpdated = technicienDao.updateDisponibilite(technicienId, disponibilite);

            response.setContentType("text/plain; charset=UTF-8");
            if (isUpdated) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Disponibilité mise à jour avec succès.");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la mise à jour de la disponibilité.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur serveur : " + e.getMessage());
        }
    }
}
