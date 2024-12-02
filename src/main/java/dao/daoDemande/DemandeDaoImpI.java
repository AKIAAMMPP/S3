package dao.daoDemande;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            String sql = "INSERT INTO demandes(client_id, service_id, demande_description, statut) VALUES(?, ?, ?, ?)";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, demande.getClientId());
            preparedStatement.setInt(2, demande.getServiceId());
            preparedStatement.setString(3, demande.getDemandeDescription());
            preparedStatement.setString(4, demande.getStatut());
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
        String sql = "SELECT * FROM demandes WHERE statut = 'en_attente' "; // Vérifiez que "demandes" est le bon nom de table

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int clientId = resultSet.getInt("client_id");
                int serviceId = resultSet.getInt("service_id");
                String demandeDescription = resultSet.getString("demande_description");
                String statut = resultSet.getString("statut");

                demandes.add(new Demande(id, clientId, serviceId, demandeDescription, statut));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des demandes.", e);
        }

        System.out.println("Demandes trouvées : " + demandes.size()); // Log supplémentaire
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
}
