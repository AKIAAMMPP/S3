package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import beans.Demande;
import beans.Intervention;
import beans.Service;
import dao.DAOFactory;
import dao.daoDemande.DemandeDAO;
import dao.daoIntervention.InterventionDAO;
import dao.daoService.ServiceDAO;

/**
 * Servlet implementation class InterventionServlet
 */
@WebServlet("/InterventionServlet")
public class InterventionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InterventionDAO interventionDao;
	private DemandeDAO demandeDao;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.interventionDao = daoFactory.getInterventionDao();
        this.demandeDao = daoFactory.getDemandeDAO();
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
                    case "modifier":
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

    private void listIntervention(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	
        	List<Intervention> interventions = interventionDao.getAllIntervention();
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
            
            Intervention intervention = new Intervention(0, Integer.parseInt(demandeId), Integer.parseInt(technicienId),
            		"hooo", "en_cours", rapport, note, commentaire);

            interventionDao.createIntervention(intervention);
            demandeDao.updateDemandeStatut(Integer.parseInt(demandeId), "terminee");
            response.sendRedirect(request.getContextPath() + "/DemandeServlet");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la création de l'intervention.");
        }
    }

}
