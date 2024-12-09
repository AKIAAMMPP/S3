package dao.daoIntervention;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.Intervention;
import dao.DAOFactory;

public class InterventionDaoImpI implements InterventionDAO {
    private DAOFactory daoFactory;

    public InterventionDaoImpI(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    
   
    @Override
    public void createIntervention(Intervention intervention) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        System.out.println("creat");
        try {
            connexion = daoFactory.getConnection();
            String sql = "INSERT INTO interventions(demande_id, technicien_id, date_intervention, statut, rapport, note, commentaire) VALUES(?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, intervention.getDemandeId());
            preparedStatement.setInt(2, intervention.getTechnicienId());
            preparedStatement.setString(3, intervention.getDateIntervention());
            preparedStatement.setString(4, intervention.getStatut());
            preparedStatement.setString(5, intervention.getRapport());
            preparedStatement.setString(6, intervention.getNote());
            preparedStatement.setString(7, intervention.getCommentaire());
            System.out.println("hihj");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
	public List<Intervention> getAllIntervention() {
    	 List<Intervention> interventions = new ArrayList<>();
         String sql = "SELECT * FROM interventions";

         try (Connection connection = daoFactory.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(sql);
              ResultSet resultSet = preparedStatement.executeQuery()) {

             while (resultSet.next()) {
                 int id = resultSet.getInt("id");
                 int demandeId = resultSet.getInt("demande_id");
                 int technicienId = resultSet.getInt("technicien_id");
                 String dateIntervention = resultSet.getString("date_intervention");
                 String statut = resultSet.getString("statut");
                 String rapport = resultSet.getString("rapport");
                 String note = resultSet.getString("note");
                 String commentaire = resultSet.getString("commentaire");

                 interventions.add(new Intervention(id, demandeId, technicienId, dateIntervention, statut, rapport, note, commentaire));
             }
         } catch (SQLException e) {
             e.printStackTrace();
             throw new RuntimeException("Erreur lors de la récupération des interventions.", e);
         }

         System.out.println("Interventions trouvées : " + interventions.size()); // Log supplémentaire
         return interventions;
	}
    
    public List<Intervention> getInterventionsByDemandeId(int demandeId) {
    	System.out.println("dkhltna "+demandeId);
        List<Intervention> interventions = new ArrayList<>();
        String query = "SELECT * FROM interventions WHERE demande_id = ? AND note = 'NONE' AND commentaire = 'NONE' AND statut = 'terminee' ";
        
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, demandeId);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("intden");
            while (resultSet.next()) {
                Intervention intervention = new Intervention();
                intervention.setId(resultSet.getInt("id"));
                intervention.setDemandeId(resultSet.getInt("demande_id"));
                intervention.setTechnicienId(resultSet.getInt("technicien_id"));
                intervention.setDateIntervention(resultSet.getString("date_intervention"));
                intervention.setStatut(resultSet.getString("statut"));
                intervention.setRapport(resultSet.getString("rapport"));
                intervention.setNote(resultSet.getString("note"));
                intervention.setCommentaire(resultSet.getString("commentaire"));
                interventions.add(intervention);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return interventions;
    }
    public List<Intervention> getInterventions_admin_ByDemandeId(int demandeId) {
    	System.out.println("Appel de getInterventions_admin_ByDemandeId avec demandeId = " + demandeId);
    	System.out.println("dkhltna ");
        List<Intervention> interventions = new ArrayList<>();
        String query = "SELECT * FROM interventions WHERE demande_id = ? AND statut = 'terminee' AND commentaire != 'NONE' AND note != 'NONE'" ;
        
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, demandeId);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("intden");
            while (resultSet.next()) {
                Intervention intervention = new Intervention();
                intervention.setId(resultSet.getInt("id"));
                intervention.setDemandeId(resultSet.getInt("demande_id"));
                intervention.setTechnicienId(resultSet.getInt("technicien_id"));
                intervention.setDateIntervention(resultSet.getString("date_intervention"));
                intervention.setStatut(resultSet.getString("statut"));
                intervention.setRapport(resultSet.getString("rapport"));
                intervention.setNote(resultSet.getString("note"));
                intervention.setCommentaire(resultSet.getString("commentaire"));
                interventions.add(intervention);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return interventions;
    }
    @Override
    public void updateIntervention(Intervention intervention) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
       
        try {
            connexion = daoFactory.getConnection();
            String sql = "UPDATE interventions SET demande_id = ?, technicien_id = ?, date_intervention = ?, statut = ?, rapport = ?, note = ?, commentaire = ? WHERE id = ?";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, intervention.getDemandeId());
            preparedStatement.setInt(2, intervention.getTechnicienId());
            preparedStatement.setString(3, intervention.getDateIntervention());
            preparedStatement.setString(4, intervention.getStatut());
            preparedStatement.setString(5, intervention.getRapport());
            preparedStatement.setString(6, intervention.getNote());
            preparedStatement.setString(7, intervention.getCommentaire());
            preparedStatement.setInt(8, intervention.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void updateIntervention_note_nom(Intervention intervention) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            System.out.println("Tentative de connexion à la base de données.");  // Message de début de connexion
            // Connexion à la base de données
            connexion = daoFactory.getConnection();
            System.out.println("Connexion réussie.");  // Message de succès de la connexion

            // Requête SQL pour mettre à jour uniquement la note et le commentaire
            String sql = "UPDATE interventions SET note = ?, commentaire = ? WHERE id = ?";
            System.out.println("Requête SQL préparée: " + sql);  // Affiche la requête SQL

            // Préparation de la requête
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, intervention.getNote());  // Paramètre pour la note
            preparedStatement.setString(2, intervention.getCommentaire());  // Paramètre pour le commentaire
            preparedStatement.setInt(3, intervention.getId());  // Paramètre pour l'ID de l'intervention
            System.out.println("Paramètres définis: note=" + intervention.getNote() + ", commentaire=" + intervention.getCommentaire() + ", id=" + intervention.getId());

            // Exécution de la mise à jour
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Mise à jour réussie. " + rowsUpdated + " ligne(s) affectée(s).");  // Message de succès
            } else {
                System.out.println("Aucune ligne mise à jour.");  // Message si aucune ligne n'est affectée
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
    @Override
    public void deleteIntervention(int id) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnection();
            String sql = "DELETE FROM interventions WHERE id = ?";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Intervention getInterventionById(int id) {
        Intervention intervention = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM interventions WHERE id = ?";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                intervention = new Intervention(
                        resultSet.getInt("id"),
                        resultSet.getInt("demande_id"),
                        resultSet.getInt("technicien_id"),
                        resultSet.getString("date_intervention"),
                        resultSet.getString("statut"),
                        resultSet.getString("rapport"),
                        resultSet.getString("note"),
                        resultSet.getString("commentaire")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connexion != null) connexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return intervention;
    }
    @Override
    public int getInterventionsEnCoursCount() {
        int count = 0;
        String query = "SELECT COUNT(*) AS inProgress FROM interventions WHERE statut = 'en_cours'";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt("inProgress");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des interventions en cours.", e);
        }

        return count;
    }
    
    public List<Map<String, Object>> getInterventionsEnCoursParTechnicien(int technicienId) throws SQLException {
        String sql = "SELECT i.id, i.statut, d.telephone, d.adresse, d.demande_description " + 
                     "FROM interventions i " +
                     "JOIN demandes d ON d.id = i.demande_id " +
                     "WHERE i.technicien_id = ? AND i.statut = 'en_cours'";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, technicienId);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Map<String, Object>> results = new ArrayList<>();
                while (resultSet.next()) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("id", resultSet.getInt("id"));
                    row.put("telephone", resultSet.getString("telephone"));
                    row.put("adresse", resultSet.getString("adresse"));
                    row.put("description", resultSet.getString("demande_description"));
                    row.put("statut", resultSet.getString("statut"));
                    results.add(row);
                }
                return results;
            }
        }
    }
    public List<Map<String, Object>> getInterventionsTerminee_ParTechnicien(Integer technicienId) throws SQLException {
        String sql = "SELECT i.id, i.statut, d.telephone, d.adresse, d.demande_description,i.note, i.commentaire " + 
                     "FROM interventions i " +
                     "JOIN demandes d ON d.id = i.demande_id " +
                     "WHERE i.technicien_id = ? AND i.statut = 'terminee'";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, technicienId);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Map<String, Object>> results = new ArrayList<>();
                while (resultSet.next()) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("id", resultSet.getInt("id"));
                    row.put("telephone", resultSet.getString("telephone"));
                    row.put("adresse", resultSet.getString("adresse"));
                    row.put("description", resultSet.getString("demande_description"));
                    row.put("note", resultSet.getString("note"));
                    row.put("commentaire", resultSet.getString("commentaire"));
                    row.put("statut", resultSet.getString("statut"));
                    results.add(row);
                }
                return results;
            }
        }
    }
    public List<Map<String, Object>> getInterventionsTermineeParTechnicien(int technicienId) throws SQLException {
        String sql = "SELECT i.id, i.statut, i.note, i.commentaire, d.telephone, d.adresse, d.demande_description " + 
                     "FROM interventions i " +
                     "JOIN demandes d ON d.id = i.demande_id " +
                     "WHERE i.technicien_id = ? AND i.statut = 'terminee'";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, technicienId);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Map<String, Object>> results = new ArrayList<>();
                while (resultSet.next()) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("id", resultSet.getInt("id"));
                    row.put("telephone", resultSet.getString("telephone"));
                    row.put("adresse", resultSet.getString("adresse"));
                    row.put("description", resultSet.getString("demande_description"));
                    row.put("statut", resultSet.getString("statut"));
                    row.put("note", resultSet.getString("note"));
                    row.put("commentaire", resultSet.getString("commenaire"));
                    results.add(row);
                }
                return results;
            }
        }
    }
    public boolean updateeStatus(int interventionId, String status) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            // Définir la requête SQL pour mettre à jour le statut
            String sql = "UPDATE interventions SET statut = ? WHERE id = ?";
            
            // Obtenir la connexion à partir de daoFactory
            connexion = daoFactory.getConnection();
            
            // Préparer la requête avec les paramètres
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, status);          // Définir le statut
            preparedStatement.setInt(2, interventionId);     // Définir l'ID de l'intervention

            // Exécuter la mise à jour
            int rowsAffected = preparedStatement.executeUpdate();
            
            // Retourne true si au moins une ligne a été mise à jour
            return rowsAffected > 0;

        } catch (SQLException e) {
            // Gérer les exceptions SQL
            e.printStackTrace();
            return false;  // Retourne false en cas d'erreur

        } finally {
            // Fermer les ressources pour éviter les fuites de mémoire
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connexion != null) connexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



	@Override
	 
    public List<Intervention> getInterventionsByClientId(Integer clientId) {
        List<Intervention> interventions = new ArrayList<>();
        String sql = "SELECT i.id AS intervention_id, i.date_intervention, i.statut, i.rapport, " +
                     "i.note, i.commentaire, s.nom AS service_name, t.nom AS technicien_name " +
                     "FROM interventions i " +
                     "JOIN demandes d ON i.demande_id = d.id " +
                     "JOIN services s ON d.service_id = s.id " +
                     "LEFT JOIN techniciens t ON i.technicien_id = t.id " +
                     "WHERE d.client_id = ? AND i.statut = 'terminee' ";
        
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, clientId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Intervention intervention = new Intervention();
                    intervention.setId(rs.getInt("intervention_id"));
                    intervention.setDateIntervention(rs.getString("date_intervention"));
                    intervention.setStatut(rs.getString("statut"));
                    intervention.setRapport(rs.getString("rapport"));
                    intervention.setNote(rs.getString("note"));
                    intervention.setCommentaire(rs.getString("commentaire"));
                    intervention.setServiceName(rs.getString("service_name"));
                    intervention.setTechnicienName(rs.getString("technicien_name"));
                    interventions.add(intervention);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return interventions;
    }



	@Override
	public List<Intervention> getCompletedInterventionsByTechnicien(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}




	
}
