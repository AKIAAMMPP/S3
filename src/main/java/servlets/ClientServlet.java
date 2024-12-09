package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import beans.Client;
import beans.Demande;
import beans.Intervention;
import beans.Service;
import beans.Technicien;
import dao.DAOFactory;
import dao.daoClient.ClientDAO;
import dao.daoDemande.DemandeDAO;
import dao.daoIntervention.InterventionDAO;
import dao.daoService.ServiceDAO;
import dao.daoTechnicien.TechnicienDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ClientServlet")
public class ClientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClientDAO clientDAO;
    private ServiceDAO serviceDAO;
    private InterventionDAO interventionDAO;
    private DemandeDAO demandeDAO;
    private TechnicienDAO technicienDAO;
	

    public void init() throws ServletException {
        // Initialisation des DAO
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.clientDAO = daoFactory.getClientDAO();
        this.serviceDAO = daoFactory.getServiceDao();
        this.interventionDAO = daoFactory.getInterventionDao();
        this.demandeDAO = daoFactory.getDemandeDAO();
        this.technicienDAO = daoFactory.getTechnicientDAO();
    }

    public ClientServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            list_Client(request, response);
        } else {
        	switch (action) {
            case "ajouter":
                showNewForm(request, response);
                break;
            case "modifier":
                showEditForm(request, response);
                break;
            case "delete":
                deleteClient(request, response);
                break;
            case "update":
                updateIntervention(request, response); // Ajout de la mise à jour de l'intervention
                break;
            case "list_intervention_client":
            	list_intervention_client(request, response);
            case "list_demandes_client":
            	list_demandes_client(request, response);
                break;
            default:
                list_Client(request, response);
                break;
        }
       }
    }
    protected void list_Client(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer clientId = (Integer) session.getAttribute("userId"); // Assurez-vous que l'ID du client est stocké dans la session
        System.out.println("clientId: " + clientId);
        
        if (clientId == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        List<Demande> demandes = demandeDAO.getDemandesByClientId(clientId); 
        System.out.println("Demandes pour clientId " + clientId + " : " + demandes);
        
        List<Intervention> interventionsClient = new ArrayList<>();
        for (Demande demande : demandes) {
            // Récupérer les interventions pour la demande
            List<Intervention> interventionsPourDemande = interventionDAO.getInterventionsByDemandeId(demande.getId());
            System.out.println("interventionsPourDemande " + interventionsPourDemande);
            
            // Récupérer le nom du service associé à la demande
            Service service = serviceDAO.getServiceById(demande.getServiceId());
            
            // Ajouter une information supplémentaire pour chaque intervention
            for (Intervention intervention : interventionsPourDemande) {
                // Récupérer le nom du technicien associé à l'intervention
                Technicien technicien = technicienDAO.getTechnicienById(intervention.getTechnicienId());
                
                // Ajouter ces informations à l'intervention pour affichage dans la JSP
                intervention.setServiceName(service != null ? service.getNom() : "Service non trouvé");
                intervention.setTechnicienName(technicien != null ? technicien.getNom() : "Technicien non trouvé");
            }
            
            interventionsClient.addAll(interventionsPourDemande);
        }
        // Récupérer les services disponibles
        List<Service> services = null;
        try {
            services = serviceDAO.getAllServices();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Map<String, Integer> demandeStats = demandeDAO.getDemandeStats();
        int interventionsEnCours = interventionDAO.getInterventionsEnCoursCount();

        // Ajouter les statistiques au contexte de la requête
        request.setAttribute("totalDemandes", demandeStats.get("totalDemandes"));
        request.setAttribute("demandesEnAttente", demandeStats.get("demandesEnAttente"));
        request.setAttribute("demandesTerminees", demandeStats.get("demandesTerminees"));
        request.setAttribute("interventionsEnCours", interventionsEnCours);
        // Ajouter les données au contexte de la requête
        request.setAttribute("services", services);
        request.setAttribute("message", "Liste des interventions et services chargée avec succès");
        request.setAttribute("isSuccess", true); // Indicateur de succès de l'opération

        // Redirection vers la page JSP
        request.getRequestDispatcher("/ClientJSP/dashboard-client.jsp").forward(request, response);
    }
    
    protected void list_demandes_client(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer la session et vérifier l'identité du client connecté
        HttpSession session = request.getSession();
        Integer clientId = (Integer) session.getAttribute("userId"); // L'ID du client stocké dans la session
        System.out.println("clientId: " + clientId);

        // Redirection vers la page de login si le client n'est pas connecté
        if (clientId == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        List<Demande> demandesClient = demandeDAO.getDemandesWithServiceNamesByClientId(clientId); 
        System.out.println("Demandes récupérées pour clientId " + clientId + " : " + demandesClient);

        Map<String, Integer> demandeStats = demandeDAO.getDemandeStats();
        int interventionsEnCours = interventionDAO.getInterventionsEnCoursCount();

        // Ajouter les statistiques au contexte de la requête
        request.setAttribute("totalDemandes", demandeStats.get("totalDemandes"));
        request.setAttribute("demandesEnAttente", demandeStats.get("demandesEnAttente"));
        request.setAttribute("demandesTerminees", demandeStats.get("demandesTerminees"));
        request.setAttribute("interventionsEnCours", interventionsEnCours);
        // Ajouter les données au contexte de la requête pour affichage dans la JSP
        request.setAttribute("demandes", demandesClient);
        request.setAttribute("message", "Liste des demandes chargée avec succès");
        request.setAttribute("isSuccess", true); // Indicateur de succès de l'opération

        request.getRequestDispatcher("/ClientJSP/dashboard-client.jsp").forward(request, response);
    }
    
    protected void list_intervention_client(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer clientId = (Integer) session.getAttribute("userId");
        if (clientId == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        List<Intervention> interventionsClient = interventionDAO.getInterventionsByClientId(clientId);
        request.setAttribute("interventions", interventionsClient);
        request.setAttribute("message", "Liste des interventions et services chargée avec succès");
        request.setAttribute("isSuccess", true);

        request.getRequestDispatcher("/ClientJSP/dashboard-client.jsp").forward(request, response);
    }


//    protected void list_intervention_client(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        Integer clientId = (Integer) session.getAttribute("userId"); // Assurez-vous que l'ID du client est stocké dans la session
//        System.out.println("clientId: " + clientId);
//        
//        if (clientId == null) {
//            response.sendRedirect("login.jsp");
//            return;
//        }
//        List<Demande> demandes = demandeDAO.getDemandesByClientId(clientId); 
//        System.out.println("Demandes pour clientId " + clientId + " : " + demandes);
//        
//        List<Intervention> interventionsClient = new ArrayList<>();
//        for (Demande demande : demandes) {
//        	
//            List<Intervention> interventionsPourDemande = interventionDAO.getInterventionsByDemandeId(demande.getId());
//            Service service = serviceDAO.getServiceById(demande.getServiceId());
//            // Ajouter une information supplémentaire pour chaque intervention
//            for (Intervention intervention : interventionsPourDemande) {
//                // Récupérer le nom du technicien associé à l'intervention
//            	System.out.println("Technicien id: " + intervention.getTechnicienId());
//                Technicien technicien = technicienDAO.getTechnicienById(intervention.getTechnicienId());
//                if (technicien != null) {
//                    System.out.println("Technicien namr: " + technicien.getNom());
//                } else {
//                    System.out.println("Technicien introuvable pour l'ID : " + intervention.getTechnicienId());
//                }
//
//                
//                // Ajouter ces informations à l'intervention pour affichage dans la JSP
//                intervention.setServiceName(service != null ? service.getNom() : "Service non trouvé");
//                intervention.setTechnicienName(technicien != null ? technicien.getNom() : "Technicien non trouvé");
//            }
//            
//            interventionsClient.addAll(interventionsPourDemande);
//        }
//        
//        System.out.println("interv pour clientId " + interventionsClient);
//        Map<String, Integer> demandeStats = demandeDAO.getDemandeStats();
//        int interventionsEnCours = interventionDAO.getInterventionsEnCoursCount();
//
//        // Ajouter les statistiques au contexte de la requête
//        request.setAttribute("totalDemandes", demandeStats.get("totalDemandes"));
//        request.setAttribute("demandesEnAttente", demandeStats.get("demandesEnAttente"));
//        request.setAttribute("demandesTerminees", demandeStats.get("demandesTerminees"));
//        request.setAttribute("interventionsEnCours", interventionsEnCours);
//        // Ajouter les données au contexte de la requête
//        request.setAttribute("interventions", interventionsClient); 
//        request.setAttribute("message", "Liste des interventions et services chargée avec succès");
//        request.setAttribute("isSuccess", true); // Indicateur de succès de l'opération
//
//        // Redirection vers la page JSP
//        request.getRequestDispatcher("/ClientJSP/dashboard-client.jsp").forward(request, response);
//    }
//    
    
    protected void updateIntervention(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Logique de mise à jour de la note et du commentaire d'une intervention
        try {
            System.out.println("Début de la mise à jour de l'intervention.");  // Message de début
            int interventionId = Integer.parseInt(request.getParameter("interventionId"));
            String newNote = request.getParameter("note");
            String newCommentaire = request.getParameter("commentaire");

            System.out.println("ID de l'intervention: " + interventionId);  // Affiche l'ID de l'intervention
            System.out.println("Nouvelle note: " + newNote);  // Affiche la note
            System.out.println("Nouveau commentaire: " + newCommentaire);  // Affiche le commentaire

            // Si la note et le commentaire ne sont pas renseignés, on les définit à "NONE" par défaut
            if (newNote == null || newNote.isEmpty()) {
                newNote = "NONE";
            }
            if (newCommentaire == null || newCommentaire.isEmpty()) {
                newCommentaire = "NONE";
            }

            // Récupérer l'intervention par ID
            Intervention intervention = interventionDAO.getInterventionById(interventionId);
            if (intervention != null) {
                System.out.println("Intervention trouvée: " + intervention);  // Affiche l'objet intervention
                // Mettre à jour la note et le commentaire de l'intervention
                intervention.setNote(newNote);
                intervention.setCommentaire(newCommentaire);

                // Sauvegarder les modifications dans la base de données
                interventionDAO.updateIntervention_note_nom(intervention);
                System.out.println("Intervention mise à jour dans la base de données.");  // Message de mise à jour réussie

                // Rediriger vers la liste des interventions
                response.sendRedirect(request.getContextPath() + "/ClientServlet");
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Intervention introuvable.");
                System.out.println("Intervention introuvable.");  // Message si l'intervention n'est pas trouvée
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la mise à jour de l'intervention: " + e.getMessage());  // Message d'erreur
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la mise à jour de l'intervention.");
        }
    }
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/ClientJSP/ClientAdd.jsp").forward(request, response);
    }

    protected void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Client client = clientDAO.getClientById(id); // Récupérer le client par son ID
        request.setAttribute("client", client); // Ajouter le client à la requête
        request.getRequestDispatcher("/ClientJSP/clientUpdate.jsp").forward(request, response); // Rediriger vers le formulaire de modification
    }

    protected void deleteClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        clientDAO.deleteClient(id); // Supprimer le client
        response.sendRedirect(request.getContextPath() + "/ClientServlet"); // Rediriger vers la liste des clients
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String adresse = request.getParameter("adresse");
            String telephone = request.getParameter("telephone");
            
            System.out.println("clikhhh");

            // Validation des champs obligatoires
            if (nom == null || prenom == null || email == null || nom.isEmpty() || password.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tous les champs sont obligatoires.");
                return;
            }
            
            Client client = new Client(0, nom, prenom, email, password, adresse, telephone);
            clientDAO.createClient(client); 
            response.sendRedirect(request.getContextPath() + "/ClientServlet"); 

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Prix invalide.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la création du client.");
        }
    }
}
