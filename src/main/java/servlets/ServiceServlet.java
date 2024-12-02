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

/**
 * Servlet implementation class ServiceServlet
 */
@WebServlet("/ServiceServlet")
public class ServiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ServiceDAO serviceDao;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.serviceDao = daoFactory.getServiceDao();
    }

    public ServiceServlet() {
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
                        deleteService(request, response);
                        break;
                    default:
                        list_Service(request, response);
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
            List<Service> services = serviceDao.getAllServices();
            
            // Vérification dans les logs pour le débogage
            System.out.println("Services récupérés : " + services);
            
            // Ajout de la liste des services dans les attributs de la requête
            request.setAttribute("services", services);
            
            // Redirection vers la page JSP
            request.getRequestDispatcher("/TechnicientJSP/technicient.jsp").forward(request, response);
        } catch (Exception e) {
            // Gestion des erreurs : log et retour d'une erreur HTTP 500
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des services.");
        }
    }


    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/ServiceJSP/serviceAdd.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID du service manquant.");
                return;
            }

            int id = Integer.parseInt(idParam);
            Service service = serviceDao.getServiceById(id);

            if (service == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Service non trouvé.");
                return;
            }

            request.setAttribute("service", service);
            request.getRequestDispatcher("/ServiceJSP/serviceUpdate.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID du service invalide.");
        }
    }

    private void deleteService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID du service manquant.");
                return;
            }

            int id = Integer.parseInt(idParam);
            serviceDao.deleteService(id);

            response.sendRedirect(request.getContextPath() + "/ServiceServlet");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID du service invalide.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la suppression du service.");
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

            Service service = new Service(0, nom, description, prix);
            serviceDao.createService(service);

            response.sendRedirect(request.getContextPath() + "/ServiceServlet");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Prix invalide.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la création du service.");
        }
    }
}
