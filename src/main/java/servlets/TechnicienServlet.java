package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import beans.Technicien;
import dao.DAOFactory;
import dao.daoDemande.DemandeDAO;
import dao.daoIntervention.InterventionDAO;
import dao.daoIntervention.getInterventionsTermineeParTechnicien;
import dao.daoTechnicien.TechnicienDAO;

/**
 * Servlet implementation class TechnicienServlet
 */
@WebServlet("/TechnicienServlet")
public class TechnicienServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TechnicienDAO technicienDao;
    private InterventionDAO interventionDAO;
    private DemandeDAO demandeDAO;
    

    @Override
    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.technicienDao = daoFactory.getTechnicientDAO();
        this.interventionDAO = daoFactory.getInterventionDao();
        this.demandeDAO = daoFactory.getDemandeDAO();
    }

    public TechnicienServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null) {
                listTechniciens(request, response);
            } else {
                switch (action) {
                    case "ajouter":
                        showNewForm(request, response);
                        break;
                        
                    case "list_technicien_admin":
                    	list_technicien_admin(request, response);
                        break;
                    case "list_intervention_terminee":
                    	list_intervention_terminee(request, response);
                        break;
                    case "delete":
                        deleteTechnicien(request, response);
                    case "list_intervention_technicien":
                    	list_intervention_technicien(request, response);
                        break;
                    case "en_cours":
                    case "termine":
                        // Mettre à jour le statut d'une intervention
                        updateInterventionStatus(request, response, action);
                        break;
                    default:
                        listTechniciens(request, response);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Une erreur s'est produite : " + e.getMessage());
        }
    }
    
    
    private void list_intervention_technicien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Integer technicienId = (Integer) session.getAttribute("userId");
            System.out.println("technicienId: " + technicienId);

            if (technicienId == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            // Appeler la méthode DAO pour récupérer les interventions en cours
            List<Map<String, Object>> interventionsEnCours1 = interventionDAO.getInterventionsTermineeParTechnicien(technicienId);
            System.out.println("interventionsEnCours: " + interventionsEnCours1);
            // Ajouter les résultats à la requête
            Map<String, Integer> demandeStats = demandeDAO.getDemandeStats();
            int interventionsEnCours = interventionDAO.getInterventionsEnCoursCount();

            // Ajouter les statistiques au contexte de la requête
            request.setAttribute("totalDemandes", demandeStats.get("totalDemandes"));
            request.setAttribute("demandesEnAttente", demandeStats.get("demandesEnAttente"));
            request.setAttribute("demandesTerminees", demandeStats.get("demandesTerminees"));
            request.setAttribute("interventionsEnCours", interventionsEnCours);
            request.setAttribute("interventions_terminee", interventionsEnCours1);

            // Rediriger vers une JSP pour afficher les données
            request.getRequestDispatcher("/TechnicientJSP/technicient.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des interventions en cours.");
        }
    }
    
    private void listTechniciens(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Integer technicienId = (Integer) session.getAttribute("userId");
            System.out.println("technicienId: " + technicienId);

            if (technicienId == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            // Appeler la méthode DAO pour récupérer les interventions en cours
            List<Map<String, Object>> interventionsEnCours1 = interventionDAO.getInterventionsEnCoursParTechnicien(technicienId);
            System.out.println("interventionsEnCours: " + interventionsEnCours1);
            // Ajouter les résultats à la requête
            Map<String, Integer> demandeStats = demandeDAO.getDemandeStats();
            int interventionsEnCours = interventionDAO.getInterventionsEnCoursCount();

            // Ajouter les statistiques au contexte de la requête
            request.setAttribute("totalDemandes", demandeStats.get("totalDemandes"));
            request.setAttribute("demandesEnAttente", demandeStats.get("demandesEnAttente"));
            request.setAttribute("demandesTerminees", demandeStats.get("demandesTerminees"));
            request.setAttribute("interventionsEnCours", interventionsEnCours);
            request.setAttribute("interventions", interventionsEnCours1);

            // Rediriger vers une JSP pour afficher les données
            request.getRequestDispatcher("/TechnicientJSP/technicient.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des interventions en cours.");
        }
    }
    private void list_intervention_terminee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Integer technicienId = (Integer) session.getAttribute("userId");
            System.out.println("technicienId: " + technicienId);

            if (technicienId == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            // Appeler la méthode DAO pour récupérer les interventions en cours
            List<Map<String, Object>> interventionsEnCours1 = interventionDAO.getInterventionsTerminee_ParTechnicien(technicienId);
            System.out.println("interventionsEnCours: " + interventionsEnCours1);
            // Ajouter les résultats à la requête
            Map<String, Integer> demandeStats = demandeDAO.getDemandeStats();
            int interventionsEnCours = interventionDAO.getInterventionsEnCoursCount();

            // Ajouter les statistiques au contexte de la requête
            request.setAttribute("totalDemandes", demandeStats.get("totalDemandes"));
            request.setAttribute("demandesEnAttente", demandeStats.get("demandesEnAttente"));
            request.setAttribute("demandesTerminees", demandeStats.get("demandesTerminees"));
            request.setAttribute("interventionsEnCours", interventionsEnCours);
            request.setAttribute("interventions_terminee", interventionsEnCours1);

            // Rediriger vers une JSP pour afficher les données
            request.getRequestDispatcher("/TechnicientJSP/technicient.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des interventions en cours.");
        }
    }

    
    private void list_technicien_admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	Map<String, Integer> demandeStats = demandeDAO.getDemandeStats();
            int interventionsEnCours = interventionDAO.getInterventionsEnCoursCount();

            // Ajouter les statistiques au contexte de la requête
            request.setAttribute("totalDemandes", demandeStats.get("totalDemandes"));
            request.setAttribute("demandesEnAttente", demandeStats.get("demandesEnAttente"));
            request.setAttribute("demandesTerminees", demandeStats.get("demandesTerminees"));
            request.setAttribute("interventionsEnCours", interventionsEnCours);
            
            List<Technicien> total_techniciens = technicienDao.getAllTechniciens();
            System.out.println("Techniciens récupérés : " + total_techniciens.size());
            
            request.setAttribute("total_techniciens", total_techniciens);
            request.getRequestDispatcher("/DemandeJSP/demandes.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des techniciens.");
        }
    }
    
    private void updateInterventionStatus(HttpServletRequest request, HttpServletResponse response, String action) throws ServletException, IOException {
        String interventionIdStr = request.getParameter("interventionId");
        String technicienIdStr = request.getParameter("technicienId");
        System.out.println("interventionId: " + interventionIdStr);
        System.out.println("technicienId: " + technicienIdStr);


        try {
            // Vérifier que les paramètres sont bien présents
            if (interventionIdStr == null || technicienIdStr == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de l'intervention ou du technicien manquant.");
                return;
            }

            // Convertir les paramètres en entiers
            int interventionId = Integer.parseInt(interventionIdStr);
            int technicienId = Integer.parseInt(technicienIdStr);

            // Vérifier l'action et définir le statut en conséquence
            String newStatus = null;
            if ("en_cours".equals(action)) {
                newStatus = "En cours"; // Statut pour une intervention en cours
            } else if ("termine".equals(action)) {
                newStatus = "Terminee"; // Statut pour une intervention terminée
            }

            if (newStatus != null) {
                // Appeler la méthode du DAO pour mettre à jour le statut de l'intervention
                boolean isUpdated = interventionDAO.updateeStatus(interventionId, newStatus);

                // Vérifier si la mise à jour a réussi
                if (isUpdated) {
                    // En cas de succès, rediriger vers la page avec un message de succès
                    response.sendRedirect("TechnicienServlet?action=list"); // Ajustez selon vos besoins
                } else {
                    // En cas d'échec de la mise à jour
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la mise à jour du statut de l'intervention.");
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action inconnue.");
            }

        } catch (NumberFormatException e) {
            // Gérer les erreurs de conversion de paramètres
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Paramètres invalides : interventionId ou technicienId doit être un nombre.");
        } catch (Exception e) {
            // Gérer les autres exceptions
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur serveur : " + e.getMessage());
        }
    }
    
//    private void listTechniciens(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//        	
//        	Map<String, Integer> demandeStats = demandeDAO.getDemandeStats();
//            int interventionsEnCours = interventionDAO.getInterventionsEnCoursCount();
//
//            // Ajouter les statistiques au contexte de la requête
//            request.setAttribute("totalDemandes", demandeStats.get("totalDemandes"));
//            request.setAttribute("demandesEnAttente", demandeStats.get("demandesEnAttente"));
//            request.setAttribute("demandesTerminees", demandeStats.get("demandesTerminees"));
//            request.setAttribute("interventionsEnCours", interventionsEnCours);
//            
//            List<Technicien> techniciens = technicienDao.getAllTechniciens();
//            request.setAttribute("techniciens", techniciens);
//            request.getRequestDispatcher("/TechnicientJSP/technicient.jsp").forward(request, response);
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des techniciens.");
//        }
//    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/TechnicienJSP/technicienAdd.jsp").forward(request, response);
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
            request.getRequestDispatcher("/TechnicienJSP/technicienUpdate.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID du technicien invalide.");
        }
    }

    private void deleteTechnicien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            updateDisponibilite(request, response);
        } else {
            // Ajouter un technicien si aucune action n'est spécifiée
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String specialite = request.getParameter("specialite");
            String experience = request.getParameter("experience");
            String service = request.getParameter("service");

            System.out.println("Ajout d'un nouveau technicien");

            // Validation des champs obligatoires
            if (nom == null || prenom == null || email == null || password == null || nom.isEmpty() || password.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tous les champs obligatoires doivent être remplis.");
                return;
            }

            // Création d'un nouvel objet Technicien
            Technicien technicien = new Technicien();
            technicien.setNom(nom);
            technicien.setPrenom(prenom);
            technicien.setEmail(email);
            technicien.setPassword(password);
            technicien.setSpecialite(specialite);
            technicien.setExperience(experience);
            technicien.setService(service);

            // Enregistrement du technicien dans la base de données
            try {
                technicienDao.createTechnicien(technicien);
                System.out.println("Technicien ajouté avec succès : " + technicien);
                response.sendRedirect(request.getContextPath() + "/TechnicienServlet?action=list_technicien_admin");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de l'ajout du technicien.");
            }
        }
    }
    
    private void updateDisponibilite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Récupérer les paramètres de la requête
            String disponibiliteParam = request.getParameter("disponibilite");
            String technicienIdStr = request.getParameter("id");

            // Vérifier si l'ID du technicien est fourni
            if (technicienIdStr == null || technicienIdStr.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "L'ID du technicien est obligatoire.");
                return;
            }

            // Valider et convertir l'ID du technicien
            int technicienId;
            try {
                technicienId = Integer.parseInt(technicienIdStr);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID du technicien invalide. L'ID doit être un nombre.");
                return;
            }

            // Valider la disponibilité
            if (disponibiliteParam == null || (!disponibiliteParam.equalsIgnoreCase("true") && !disponibiliteParam.equalsIgnoreCase("false"))) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Le paramètre 'disponibilite' doit être 'true' ou 'false'.");
                return;
            }

            boolean disponibilite = Boolean.parseBoolean(disponibiliteParam);

            // Appeler la méthode DAO pour mettre à jour la disponibilité
            boolean isUpdated = technicienDao.updateDisponibilite(technicienId, disponibilite);

            // Configurer la réponse HTTP
            response.setContentType("text/plain; charset=UTF-8");

            response.sendRedirect(request.getContextPath() + "/TechnicienServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur serveur : " + e.getMessage());
        }
    }

}
