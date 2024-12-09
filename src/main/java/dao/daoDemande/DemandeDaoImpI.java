package dao.daoDemande;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.Demande;
import dao.DAOFactory;

public class DemandeDaoImpI implements DemandeDAO {
    private DAOFactory daoFactory;

    public DemandeDaoImpI(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void createDemande(Demande demande) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnection();
            String sql = "INSERT INTO demandes(client_id, service_id, demande_description, adresse, telephone, statut) VALUES(?, ?, ?, ?, ?, ?)";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, demande.getClientId());
            preparedStatement.setInt(2, demande.getServiceId());
            preparedStatement.setString(3, demande.getDemandeDescription());
            preparedStatement.setString(4, demande.getAdresse());
            preparedStatement.setString(5, demande.getTelephone());
            preparedStatement.setString(6, demande.getStatut());
            System.out.println("hiiiiiiiiiiii");

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
    public List<Demande> getAllDemandes() {
        List<Demande> demandes = new ArrayList<>();
        String sql = "SELECT * FROM demandes WHERE statut='en_attente'"; // Vérifiez que "demandes" est le bon nom de table

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int clientId = resultSet.getInt("client_id");
                int serviceId = resultSet.getInt("service_id");
                String demandeDescription = resultSet.getString("demande_description");
                String adresse = resultSet.getString("adresse");
                String telephone = resultSet.getString("telephone");
                String statut = resultSet.getString("statut");

                demandes.add(new Demande(id, clientId, serviceId, demandeDescription, adresse, telephone, statut));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des demandes.", e);
        }

        System.out.println("Demandes trouvées : " + demandes.size()); // Log supplémentaire
        return demandes;
    }
    @Override
    public List<Demande> getAllDemandes_pour_intervention() {
        List<Demande> demandes = new ArrayList<>();
        String sql = "SELECT * FROM demandes"; // Vérifiez que "demandes" est le bon nom de table

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int clientId = resultSet.getInt("client_id");
                int serviceId = resultSet.getInt("service_id");
                String demandeDescription = resultSet.getString("demande_description");
                String adresse = resultSet.getString("adresse");
                String telephone = resultSet.getString("telephone");
                String statut = resultSet.getString("statut");

                demandes.add(new Demande(id, clientId, serviceId, demandeDescription, adresse, telephone, statut));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des demandes.", e);
        }

        System.out.println("Demandes trouvées : " + demandes.size()); // Log supplémentaire
        return demandes;
    }
    @Override
    public List<Demande> getDemandesWithServiceNamesByClientId(Integer clientId) {
        List<Demande> demandes = new ArrayList<>();
        String query = "SELECT " +
                       "    demandes.id AS id, " +
                       "    demandes.demande_description AS demande_description, " +
                       "    demandes.statut, " +
                       "    services.nom AS serviceName " +
                       "FROM demandes " +
                       "JOIN services ON demandes.service_id = services.id " +
                       "WHERE demandes.client_id = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clientId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Demande demande = new Demande();
                demande.setId(rs.getInt("id"));
                demande.setDemandeDescription(rs.getString("demande_description"));
                demande.setStatut(rs.getString("statut"));
                demande.setServiceName(rs.getString("serviceName")); // Champ serviceName
                demandes.add(demande);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return demandes;
    }

    public List<Demande> getDemandesByClientId(int clientId) {
    	System.out.println("hana " ); // Log supplémentaire
        List<Demande> demandes = new ArrayList<>();
        String query = "SELECT * FROM demandes WHERE client_id = ?";
        
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            // On assigne correctement le paramètre clientId à la requête préparée
            statement.setInt(1, clientId);  // Correctement passé en tant que paramètre à la requête
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Demande demande = new Demande();
                    demande.setId(resultSet.getInt("id"));
                    demande.setClientId(resultSet.getInt("client_id"));
                    demande.setServiceId(resultSet.getInt("service_id"));
                    demande.setDemandeDescription(resultSet.getString("demande_description"));
                    demande.setAdresse(resultSet.getString("adresse"));;
                    demande.setTelephone(resultSet.getString("telephone"));
                    demande.setStatut(resultSet.getString("statut"));
                    demandes.add(demande);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return demandes;
    }
    


    @Override
    public void updateDemandeStatut(int demandeId, String newStatut) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnection();
            String sql = "UPDATE demandes SET statut = ? WHERE id = ?";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, newStatut);
            preparedStatement.setInt(2, demandeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer les ressources
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
    public void updateDemande(Demande demande) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnection();
            String sql = "UPDATE demandes SET client_id = ?, service_id = ?, demande_description = ?, statut = ? WHERE id = ?";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, demande.getClientId());
            preparedStatement.setInt(2, demande.getServiceId());
            preparedStatement.setString(3, demande.getDemandeDescription());
            preparedStatement.setString(4, demande.getStatut());
            preparedStatement.setInt(5, demande.getId());

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
    public void deleteDemande(int id) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnection();
            String sql = "DELETE FROM demandes WHERE id = ?";
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
    public Demande getDemandeById(int id) {
        Demande demande = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM demandes WHERE id = ?";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                demande = new Demande(
                        resultSet.getInt("id"),
                        resultSet.getInt("client_id"),
                        resultSet.getInt("service_id"),
                        resultSet.getString("demande_description"),
                        resultSet.getString("adresse"),
                        resultSet.getString("telephone"),
                        resultSet.getString("statut")
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

        return demande;
    }
    @Override
    public Map<String, Integer> getDemandeStats() {
        Map<String, Integer> stats = new HashMap<>();
        String totalQuery = "SELECT COUNT(*) AS total FROM demandes";
        String pendingQuery = "SELECT COUNT(*) AS pending FROM demandes WHERE statut = 'en_attente'";
        String completedQuery = "SELECT COUNT(*) AS completed FROM demandes WHERE statut = 'terminee'";

        try (Connection connection = daoFactory.getConnection()) {
            // Total des demandes
            try (PreparedStatement stmt = connection.prepareStatement(totalQuery);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    stats.put("totalDemandes", rs.getInt("total"));
                }
            }

            // Demandes en attente
            try (PreparedStatement stmt = connection.prepareStatement(pendingQuery);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    stats.put("demandesEnAttente", rs.getInt("pending"));
                }
            }

            // Demandes terminées
            try (PreparedStatement stmt = connection.prepareStatement(completedQuery);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    stats.put("demandesTerminees", rs.getInt("completed"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des statistiques des demandes.", e);
        }

        return stats;
    }
    

}
