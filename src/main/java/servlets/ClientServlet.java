package servlets;

import java.io.IOException;
import java.util.List;

import beans.Client;
import beans.Service;
import dao.DAOFactory;
import dao.daoClient.ClientDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ClientServlet")
	public class ClientServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private ClientDAO clientDAO;
	    
	    public void init() throws ServletException {
	        DAOFactory daoFactory = DAOFactory.getInstance();
	        this.clientDAO = daoFactory.getClientDAO();
	    }

	    public ClientServlet() {
	        super();
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getParameter("action");

	        if (action == null) {
	            list_Client(request, response); // Appeler la méthode qui liste les clients
	        } else {
	            switch (action) {
		            case "ajouter":
		            	showNewForm(request, response); // Afficher le formulaire de modification
	                    break;
	                case "modifier":
	                    showEditForm(request, response); // Afficher le formulaire de modification
	                    break;
	                case "delete":
	                    deleteClient(request, response); // Supprimer le client
	                    break;
	                default:
	                    list_Client(request, response); // Afficher la liste des clients pour une action inconnue
	                    break;
	            }
	        }
	    }

	    protected void list_Client(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        List<Client> clients = clientDAO.getAllClients(); // Récupérer la liste des clients
	        request.setAttribute("clients", clients); // Ajouter la liste des clients à la requête
	        request.getRequestDispatcher("/ClientJSP/clients.jsp").forward(request, response);
	        System.out.println("hhhh");
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
	            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la création du service.");
	        }
	    }
	    
	}


