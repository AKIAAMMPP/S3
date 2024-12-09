package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import beans.Service;
import dao.DAOFactory;
import dao.daoDemande.DemandeDAO;
import dao.daoIntervention.InterventionDAO;
import dao.daoService.ServiceDAO;

/**
 * Servlet implementation class ServiceServlet
 */
@WebServlet("/ServiceServlet")
public class ServiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ServiceDAO serviceDao;
    private InterventionDAO interventionDao;
	private DemandeDAO demandeDao;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.serviceDao = daoFactory.getServiceDao();
        this.interventionDao = daoFactory.getInterventionDao();
        this.demandeDao = daoFactory.getDemandeDAO();
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
                    case "list_service_admin":
                    	list_service_admin(request, response);
                        break;
                    case "modifier":
                        showEditForm(request, response);
                        break;
                    case "filtrer": // Ajout du cas pour filtrer les services
                        filtrerServices(request, response);
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
    private void list_service_admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Récupération des services depuis la base de données via le DAO
            List<Service> services = serviceDao.getAllServices();
            
            // Vérification dans les logs pour le débogage
            System.out.println("Services récupérés : " + services);
            Map<String, Integer> demandeStats = demandeDao.getDemandeStats();
            int interventionsEnCours = interventionDao.getInterventionsEnCoursCount();

            // Ajouter les statistiques au contexte de la requête
            request.setAttribute("totalDemandes", demandeStats.get("totalDemandes"));
            request.setAttribute("demandesEnAttente", demandeStats.get("demandesEnAttente"));
            request.setAttribute("demandesTerminees", demandeStats.get("demandesTerminees"));
            request.setAttribute("interventionsEnCours", interventionsEnCours);
            // Ajout de la liste des services dans les attributs de la requête
            request.setAttribute("services_s", services);
            
            // Redirection vers la page JSP
            request.getRequestDispatcher("/DemandeJSP/demandes.jsp").forward(request, response);
        } catch (Exception e) {
            // Gestion des erreurs : log et retour d'une erreur HTTP 500
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des services.");
        }
    }
    
    private void list_Service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Récupération des services depuis la base de données via le DAO
            List<Service> services = serviceDao.getAllServices();
            
            // Vérification dans les logs pour le débogage
            System.out.println("Services récupérés : " + services);
            Map<String, Integer> demandeStats = demandeDao.getDemandeStats();
            int interventionsEnCours = interventionDao.getInterventionsEnCoursCount();

            // Ajouter les statistiques au contexte de la requête
            request.setAttribute("totalDemandes", demandeStats.get("totalDemandes"));
            request.setAttribute("demandesEnAttente", demandeStats.get("demandesEnAttente"));
            request.setAttribute("demandesTerminees", demandeStats.get("demandesTerminees"));
            request.setAttribute("interventionsEnCours", interventionsEnCours);
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
    private void filtrerServices(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Récupérer le terme de recherche depuis les paramètres de la requête
            String searchQuery = request.getParameter("search");

            // Vérifier que le terme de recherche n'est pas vide ou null
            if (searchQuery == null || searchQuery.trim().isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/ServiceServlet?action=list_service_admin");
                return;
            }

            // Récupérer les services correspondant au critère de recherche
            List<Service> filteredServices = serviceDao.getServicesByName(searchQuery);
            Map<String, Integer> demandeStats = demandeDao.getDemandeStats();
            int interventionsEnCours = interventionDao.getInterventionsEnCoursCount();

            // Ajouter les statistiques au contexte de la requête
            request.setAttribute("totalDemandes", demandeStats.get("totalDemandes"));
            request.setAttribute("demandesEnAttente", demandeStats.get("demandesEnAttente"));
            request.setAttribute("demandesTerminees", demandeStats.get("demandesTerminees"));
            request.setAttribute("interventionsEnCours", interventionsEnCours);
            // Vérification des logs pour le débogage
            System.out.println("Services filtrés pour la recherche '" + searchQuery + "': " + filteredServices);

            // Ajouter les services filtrés dans l'objet request
            request.setAttribute("services", filteredServices);

            // Rediriger vers la JSP appropriée
            request.getRequestDispatcher("/ClientJSP/dashboard-client.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors du filtrage des services.");
        }
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

            response.sendRedirect(request.getContextPath() + "/DemandeServlet");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID du service invalide.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la suppression du service.");
        }
    }
    private void updateService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Récupérer les paramètres du formulaire
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            String description = request.getParameter("description");
            double prix = Double.parseDouble(request.getParameter("prix"));

            // Créer un objet Service avec les nouvelles données
            Service service = new Service();
            service.setId(id);
            service.setNom(nom);
            service.setDescription(description);
            service.setPrix(prix);

            // Appeler la méthode DAO pour mettre à jour le service
            serviceDao.updateService(service);

            // Rediriger vers la liste des services
            response.sendRedirect(request.getContextPath() + "/ServiceServlet?action=list_service_admin");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la mise à jour du service.");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("update".equals(action)) {
                updateService(request, response); // Appel de la méthode pour mettre à jour le service
            } else if ("delete".equals(action)) {
                deleteService(request, response); // Appel de la méthode pour supprimer le service
            } else {
                // Création d'un nouveau service
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

                response.sendRedirect(request.getContextPath() + "/ServiceServlet?action=list_service_admin");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Prix invalide.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la création du service.");
        }
    }

}
