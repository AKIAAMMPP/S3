package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import beans.Demande;
import beans.Service;
import beans.Technicien;
import dao.DAOFactory;
import dao.daoDemande.DemandeDAO;
import dao.daoService.ServiceDAO;
import dao.daoTechnicien.TechnicienDAO;

/**
 * Servlet implementation class DemandeServlet
 */
@WebServlet("/DemandeServlet")
public class DemandeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DemandeDAO demandeDao;
    private TechnicienDAO technicienDao;
    private ServiceDAO serviceDao;


        

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.demandeDao = daoFactory.getDemandeDAO();
        this.technicienDao = daoFactory.getTechnicientDAO();
        this.serviceDao = daoFactory.getServiceDao();
    }

    public DemandeServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null) {
            	listDemande(request, response);
            } else {
                switch (action) {
                    case "ajouter":
                        showNewForm(request, response);
                        break;
                    case "modifier":
                        showEditForm(request, response);
                        break;
                    case "delete":
                        deleteDemande(request, response);
                        break;
                    default:
                    	listDemande(request, response);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Une erreur s'est produite : " + e.getMessage());
        }
    }

    private void listDemande(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	List<Technicien> techniciens = technicienDao.getAllTechniciens();
        	List<Demande> demandes = demandeDao.getAllDemandes();
        	// Récupérer le nom du service pour chaque demande
            for (Demande demande : demandes) {
                Service service = serviceDao.getServiceById(demande.getServiceId());
                if (service != null) {
                    demande.setServiceName(service.getNom());  // Assigner le nom du service à l'objet Demande
                }
            }
            String demandeId = request.getParameter("demandeId");
            request.setAttribute("techniciens", techniciens);
            request.setAttribute("demandeId", demandeId); 
        	request.setAttribute("demandes", demandes); 
        	request.getRequestDispatcher("/DemandeJSP/demandes.jsp").forward(request, response);
            System.out.println("Demandes récupérées : " + demandes);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des demandes.");
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'ID du service à partir de la requête
        String serviceId = request.getParameter("serviceId");
        
        // Passer serviceId à la JSP
        request.setAttribute("serviceId", serviceId);
        
        // Rediriger vers la page de formulaire
        request.getRequestDispatcher("/DemandeJSP/demandeAdd.jsp").forward(request, response);
    }



    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de la demande manquant.");
                return;
            }

            int id = Integer.parseInt(idParam);
            Demande demande = demandeDao.getDemandeById(id);

            if (demande == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Demande non trouvée.");
                return;
            }

            request.setAttribute("demande", demande);
            request.getRequestDispatcher("/DemandeJSP/demandeUpdate.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de la demande invalide.");
        }
    }

    private void deleteDemande(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de la demande manquant.");
                return;
            }

            int id = Integer.parseInt(idParam);
            demandeDao.deleteDemande(id);

            response.sendRedirect(request.getContextPath() + "/DemandeServlet");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de la demande invalide.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la suppression de la demande.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Récupérer les paramètres envoyés par le formulaire
            String serviceIdParam = request.getParameter("serviceId");
            String description = request.getParameter("description");
            String statut = request.getParameter("statut");

            // Afficher les valeurs des paramètres pour le débogage
            System.out.println("Paramètre serviceId : " + serviceIdParam);
            System.out.println("Paramètre description : " + description);
            System.out.println("Paramètre statut : " + statut);

            // Vérifier que l'utilisateur est connecté
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("userId") == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Vous devez être connecté pour effectuer cette action.");
                return;
            }

            // Afficher l'ID utilisateur pour le débogage
            int userId = (int) session.getAttribute("userId");
            System.out.println("Utilisateur connecté, ID : " + userId);

            // Validation des paramètres
            if (serviceIdParam == null || description == null || serviceIdParam.isEmpty() || description.isEmpty()) {
               
                System.out.println("Validation échouée : champs obligatoires manquants.");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tous les champs sont obligatoires.");
                return;
            }
            // Convertir l'ID de service
            int serviceId = Integer.parseInt(serviceIdParam);
            System.out.println("Conversion réussie : serviceId = " + serviceId);

            // Créer une nouvelle instance de Demande
            Demande demande = new Demande(0, userId, serviceId, description, statut);
            System.out.println("Demande créée : " + demande);
            
            demandeDao.createDemande(demande);

            // Rediriger vers une page de succès ou la liste des demandes
            response.sendRedirect(request.getContextPath() + "/DemandeServlet?success=created");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Erreur de conversion : " + e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de service invalide.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur interne : " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la création de la demande.");
        }
    }



}
