package servlets;
import java.time.LocalDate; // Pour la date seulement
import java.time.format.DateTimeFormatter; // Pour formater la date

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import beans.Demande;
import beans.Intervention;
import beans.Service;
import beans.Technicien;
import dao.DAOFactory;
import dao.daoDemande.DemandeDAO;
import dao.daoDemande.getAllDemandes_pour_intervention;
import dao.daoIntervention.InterventionDAO;
import dao.daoService.ServiceDAO;
import dao.daoTechnicien.TechnicienDAO;

/**
 * Servlet implementation class InterventionServlet
 */
@WebServlet("/InterventionServlet")
public class InterventionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InterventionDAO interventionDao;
	private DemandeDAO demandeDao;
	private ServiceDAO serviceDao;
	private TechnicienDAO technicienDao;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.interventionDao = daoFactory.getInterventionDao();
        this.demandeDao = daoFactory.getDemandeDAO();
        this.serviceDao = daoFactory.getServiceDao();
        this.technicienDao = daoFactory.getTechnicientDAO();
    }

    public InterventionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null) {
            	listIntervention(request, response);
            } else {
                switch (action) {
                    case "ajouter":
                        break;
                    case "list_intervention_admin":
                    	list_intervention_admin(request, response);
                        break;
                    case "delete":
                        break;
                    default:
                    	listIntervention(request, response);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Une erreur s'est produite : " + e.getMessage());
        }
    }
    
    
    protected void list_intervention_admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        
    	List<Demande> demandes = demandeDao.getAllDemandes_pour_intervention(); 
    	System.out.println("demnsdidii " + demandes);
        List<Intervention> interventionsClient = new ArrayList<>();
        for (Demande demande : demandes) {
            // Récupérer les interventions pour la demande
            List<Intervention> interventionsPourDemande = interventionDao.getInterventions_admin_ByDemandeId(demande.getId());
            
            // Récupérer le nom du service associé à la demande
            Service service = serviceDao.getServiceById(demande.getServiceId());
            
            // Ajouter une information supplémentaire pour chaque intervention
            for (Intervention intervention : interventionsPourDemande) {
                // Récupérer le nom du technicien associé à l'intervention
                Technicien technicien = technicienDao.getTechnicienById(intervention.getTechnicienId());
                
                // Ajouter ces informations à l'intervention pour affichage dans la JSP
                intervention.setServiceName(service != null ? service.getNom() : "Service non trouvé");
                intervention.setTechnicienName(technicien != null ? technicien.getNom() : "Technicien non trouvé");
            }
            
            interventionsClient.addAll(interventionsPourDemande);
        }
        System.out.println("interv pour clientId " + interventionsClient);
        Map<String, Integer> demandeStats = demandeDao.getDemandeStats();
        int interventionsEnCours = interventionDao.getInterventionsEnCoursCount();

        // Ajouter les statistiques au contexte de la requête
        request.setAttribute("totalDemandes", demandeStats.get("totalDemandes"));
        request.setAttribute("demandesEnAttente", demandeStats.get("demandesEnAttente"));
        request.setAttribute("demandesTerminees", demandeStats.get("demandesTerminees"));
        request.setAttribute("interventionsEnCours", interventionsEnCours);
        // Ajouter les données au contexte de la requête
        request.setAttribute("interventions", interventionsClient); 
        request.setAttribute("message", "Liste des interventions et services chargée avec succès");
        request.setAttribute("isSuccess", true); // Indicateur de succès de l'opération

        // Redirection vers la page JSP
        request.getRequestDispatcher("/DemandeJSP/demandes.jsp").forward(request, response);
    }
    

    private void listIntervention(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	
        	List<Intervention> interventions = interventionDao.getAllIntervention();
        	Map<String, Integer> demandeStats = demandeDao.getDemandeStats();
            int interventionsEnCours = interventionDao.getInterventionsEnCoursCount();

            // Ajouter les statistiques au contexte de la requête
            request.setAttribute("totalDemandes", demandeStats.get("totalDemandes"));
            request.setAttribute("demandesEnAttente", demandeStats.get("demandesEnAttente"));
            request.setAttribute("demandesTerminees", demandeStats.get("demandesTerminees"));
            request.setAttribute("interventionsEnCours", interventionsEnCours);
        	request.setAttribute("interventions", interventions); 
        	request.getRequestDispatcher("/DemandeJSP/demandes.jsp").forward(request, response);
            System.out.println("interventions récupérées : " + interventions);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des interventions.");
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Récupérer les données du formulaire
            String demandeId = request.getParameter("demandeId");
            String technicienId = request.getParameter("technicienId");
            String rapport = request.getParameter("rapport");
            String note = request.getParameter("note");
            String commentaire = request.getParameter("commentaire");
            
            System.out.println("Paramètre d : " + demandeId);
            System.out.println("Paramètre t : " + technicienId);
            System.out.println("Paramètre r : " + rapport);
            System.out.println("Paramètre n : " + note);
            System.out.println("Paramètre c : " + commentaire);

            // Validation de l'entrée
            if (demandeId == null || technicienId == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Les champs Demande et Technicien sont obligatoires.");
                return;
            }
            // Générer la date d'aujourd'hui au format désiré (par exemple : "yyyy-MM-dd")
            LocalDate today = LocalDate.now();
            String dateAujourdhui = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            
            // Créer une nouvelle intervention
            Intervention intervention = new Intervention(0, Integer.parseInt(demandeId), Integer.parseInt(technicienId),
            		dateAujourdhui, "en_cours", rapport, note, commentaire);

            // Ajouter l'intervention dans la base de données
            interventionDao.createIntervention(intervention);
            demandeDao.updateDemandeStatut(Integer.parseInt(demandeId), "terminee");
            response.sendRedirect(request.getContextPath() + "/DemandeServlet");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la création de l'intervention.");
        }
    }
	

}
