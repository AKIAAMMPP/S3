package dao.daoIntervention;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	
}
