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
            	list_Technicient(request, response);
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
    
    

    private void list_Technicient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	List<Technicien> technicients = technicienDao.getAllTechniciens();
            request.setAttribute("technicients", technicients);
            request.getRequestDispatcher("/TechnicientJSP/technicient.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des techniciens.");
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/TechnicientJSP/technicientAdd.jsp").forward(request, response);
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
            request.getRequestDispatcher("/TechnicientJSP/technicienUpdate.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID du technicien invalide.");
        }
    }

    private void deleteTechnicient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            updateDisponibilite(request,response);
        }
        //try {
            // Récupération de l'action
            //String action = request.getParameter("action");

            //if ("createTechnicien".equals(action)) {
                // Gestion de la création du technicien
              //  String nom = request.getParameter("nom");
               // String prenom = request.getParameter("prenom");
                //String email = request.getParameter("email");
                //String specialite = request.getParameter("specialite");
                //String experience = request.getParameter("experience");
                //String password = request.getParameter("password");

                // Validation des champs obligatoires
                /*if (nom == null || prenom == null || email == null || specialite == null || experience == null || password == null ||
                    nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || specialite.isEmpty() || password.isEmpty()) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tous les champs sont obligatoires.");
                    return;
                }

                // Création de l'objet Technicien
                Technicien technicien = new Technicien();
                technicien.setNom(nom);
                technicien.setPrenom(prenom);
                technicien.setEmail(email);
                technicien.setSpecialite(specialite);
                technicien.setExperience(experience);
                technicien.setPassword(password);

                // Ajout du technicien dans la base de données via le DAO
                technicienDao.createTechnicien(technicien);

                // Redirection après ajout
                response.sendRedirect(request.getContextPath() + "/TechnicienServlet");*/
            } 
    private void updateDisponibilite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            	
    	String disponibiliteParam = request.getParameter("disponibilite"); 
    	String technicienIdStr = request.getParameter("id"); // ID du technicien

    	// Vérification des paramètres
    	if (technicienIdStr == null || technicienIdStr.isEmpty()) {
    	    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID du technicien est obligatoire.");
    	    return;
    	}

    	if (disponibiliteParam == null || disponibiliteParam.isEmpty()) {
    	    // Si le paramètre "disponibilite" est absent ou vide, cela signifie que la case n'est pas cochée
    	    disponibiliteParam = "false"; // Par défaut, on considère que le technicien est indisponible
    	}

    	// Convertir la valeur en booléen
    	boolean disponibilite = "true".equals(disponibiliteParam);

    	// Convertir l'ID en entier
    	int technicienId = Integer.parseInt(technicienIdStr);

    	// Mise à jour de la disponibilité
    	boolean isUpdated = technicienDao.updateDisponibilite(technicienId, disponibilite);

    	if (isUpdated) {
    	    response.setStatus(HttpServletResponse.SC_OK);
    	    response.getWriter().write("Disponibilité mise à jour avec succès.");
    	} else {
    	    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la mise à jour de la disponibilité.");
    	}


                
                 
         
    }}


            

            

        
    



