package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import beans.Client;
import beans.Intervention;
import beans.Service;
import dao.DAOFactory;
import dao.daoClient.ClientDAO;
import dao.daoIntervention.InterventionDAO;
import dao.daoService.ServiceDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ClientServlet")
public class ClientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClientDAO clientDAO;
    private ServiceDAO serviceDAO;
    private InterventionDAO interventionDAO;
	

    public void init() throws ServletException {
        // Initialisation des DAO
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.clientDAO = daoFactory.getClientDAO();
        this.serviceDAO = daoFactory.getServiceDao();
        this.interventionDAO = daoFactory.getInterventionDao();
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
                default:
                    list_Client(request, response);
                    break;
            }
        }
    }

    protected void list_Client(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        List<Client> clients = clientDAO.getAllClients(); 
        List<Intervention> interventions = interventionDAO.getAllIntervention();
    	
        List<Service> services = null;
		try {
			services = serviceDAO.getAllServices();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // Variables supplémentaires à envoyer
        String message = "Liste des clients et services chargée avec succès";
        boolean isSuccess = true; // Indicateur de succès de l'opération
        
        request.setAttribute("interventions", interventions); 
        request.setAttribute("clients", clients);
        request.setAttribute("services", services);
        request.setAttribute("message", message);  // Ajouter le message
        request.setAttribute("isSuccess", isSuccess);  // Ajouter l'indicateur de succès
        
        // Redirection vers la page JSP
        request.getRequestDispatcher("/ClientJSP/dashboard-client.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Rediriger vers le formulaire d'ajout de client
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

            // Validation des champs obligatoires
            if (nom == null || prenom == null || email == null || nom.isEmpty() || password.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tous les champs sont obligatoires.");
                return;
            }

            // Création du client
            Client client = new Client(0, nom, prenom, email, password, adresse, telephone);
            clientDAO.createClient(client); 

            // Redirection vers la liste des clients
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
