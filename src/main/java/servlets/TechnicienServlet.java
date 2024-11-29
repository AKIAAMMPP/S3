package servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import beans.Service;
import dao.DAOFactory;
import dao.daoService.ServiceDAO;
import dao.daoTechnicien.TechnicienDAO;

/**
 * Servlet implementation class TechnicienServlet
 */
@WebServlet("/TechnicienServlet")
public class TechnicienServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TechnicienDAO technicienDao;

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
                list_Service(request, response);
            } else {
                switch (action) {
                    case "ajouter":
                        showNewForm(request, response);
                        break;
                    case "modifier":
                        showEditForm(request, response);
                        break;
                    case "delete":
                        deleteTechnicient(request, response);
                        break;
                    default:
                        list_Technicient(request, response);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Une erreur s'est produite : " + e.getMessage());
        }
    }
    
    private void list_Service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Récupération des services depuis la base de données via le DAO
            List<Service> technicients = technicienDao.getAllTechnicients();
            
            // Vérification dans les logs pour le débogage
            System.out.println("Technicient récupérés : " + technicients);
            
            // Ajout de la liste des services dans les attributs de la requête
            request.setAttribute("technicients", technicients);
            
            // Redirection vers la page JSP
            request.getRequestDispatcher("/TechnicientJSP/technicients.jsp").forward(request, response);
        } catch (Exception e) {
            // Gestion des erreurs : log et retour d'une erreur HTTP 500
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des services.");
        }
    }


    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/TechnicientJSP/technicientAdd.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID du Technicient manquant.");
                return;
            }

            int id = Integer.parseInt(idParam);
            Service service = technicienDao.getTechnicientById(id);

            if (service == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Technicient non trouvé.");
                return;
            }

            request.setAttribute("technicient", service);
            request.getRequestDispatcher("/TechnicientJSP/technicientUpdate.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID du Technicient invalide.");
        }
    }

    private void deleteTechnicient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID du Technicient manquant.");
                return;
            }

            int id = Integer.parseInt(idParam);
            technicienDao.deleteTechnicient(id);

            response.sendRedirect(request.getContextPath() + "/TechnicienServlet");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID du Technicient invalide.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la suppression du Technicient.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nom = request.getParameter("nom");
            String description = request.getParameter("description");
            String prixParam = request.getParameter("prix");

            if (nom == null || description == null || prixParam == null || nom.isEmpty() || description.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tous les champs sont obligatoires.");
                return;
            }

            double prix = Double.parseDouble(prixParam);

            Service technicient = new Service(0, nom, description, prix);
            technicienDao.createTechnicient(technicient);

            response.sendRedirect(request.getContextPath() + "/TechnicienServlet");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Prix invalide.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la création du Technicient.");
        }
    }
}
