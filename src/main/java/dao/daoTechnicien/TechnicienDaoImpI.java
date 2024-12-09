package dao.daoTechnicien;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Technicien;
import dao.DAOFactory;

public class TechnicienDaoImpI implements TechnicienDAO {
    private DAOFactory daoFactory;

    public TechnicienDaoImpI(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void createTechnicien(Technicien t) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnection();
            // La requête SQL avec les bons paramètres
            String sql = "INSERT INTO techniciens(nom, prenom, experience, specialite, email, password, service) VALUES(?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connexion.prepareStatement(sql);

            // Assurez-vous que vous utilisez les bons indices pour les paramètres
            preparedStatement.setString(1, t.getNom());           // nom
            preparedStatement.setString(2, t.getPrenom());        // prenom
            preparedStatement.setString(3, t.getExperience());    // experience
            preparedStatement.setString(4, t.getSpecialite());    // specialite
            preparedStatement.setString(5, t.getEmail());         // email
            preparedStatement.setString(6, t.getPassword());
            preparedStatement.setString(7, t.getService());// password

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
    public List<Technicien> getAllTechniciens() {
        List<Technicien> techniciens = new ArrayList<>();
        String sql = "SELECT * FROM techniciens";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String specialite = resultSet.getString("specialite");
                String experience = resultSet.getString("experience");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String service = resultSet.getString("service");
                

                techniciens.add(new Technicien(id, nom, prenom, specialite, experience, email, password, false, service));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des techniciens.", e);
        }

        return techniciens;
    }
    @Override
    public List<Technicien> getAllTechniciens_disponible() {
        List<Technicien> techniciens = new ArrayList<>();
        String sql = "SELECT * FROM techniciens WHERE disponibilite = 1 ";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String specialite = resultSet.getString("specialite");
                String experience = resultSet.getString("experience");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String service = resultSet.getString("service");
                

                techniciens.add(new Technicien(id, nom, prenom, specialite, experience, email, password, false, service));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des techniciens.", e);
        }

        return techniciens;
    }

    @Override
    public void updateTechnicien(Technicien t) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnection();
            String sql = "UPDATE techniciens SET nom = ?, prenom = ?, specialite = ?, salaire = ? WHERE id = ?";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, t.getNom());
            preparedStatement.setString(2, t.getPrenom());
            preparedStatement.setString(3, t.getSpecialite());
            preparedStatement.setInt(5, t.getId());

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
    public void deleteTechnicien(int id) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnection();
            String sql = "DELETE FROM techniciens WHERE id = ?";
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
    public boolean updateDisponibilite(int id, boolean disponibilite) {
        // La requête SQL pour mettre à jour la disponibilité
        String sql = "UPDATE techniciens SET disponibilite = ? WHERE id = ?";

        // Utilisation du bloc try-with-resources pour gérer automatiquement la fermeture des ressources
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(sql)) {

            // Définir les paramètres de la requête
            preparedStatement.setBoolean(1, disponibilite);
            preparedStatement.setInt(2, id);

            // Exécuter la mise à jour et vérifier le nombre de lignes affectées
            int rowsAffected = preparedStatement.executeUpdate();

            // Retourner true si une ou plusieurs lignes sont mises à jour
            return rowsAffected > 0;

        } catch (SQLException e) {
            // Afficher un message d'erreur plus descriptif
            System.err.println("Erreur lors de la mise à jour de la disponibilité du technicien avec ID : " + id);
            e.printStackTrace();
            return false; // Retourne false en cas d'échec
        }
    }

    @Override
    public Technicien getTechnicienById(int id) {
        Technicien technicien = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Etape 1 : Connexion
            connexion = daoFactory.getConnection();
            System.out.println("Connexion établie avec succès.");

            // Etape 2 : Préparer et exécuter la requête
            String sql = "SELECT * FROM techniciens WHERE id = ?";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            System.out.println("Requête préparée : " + sql);

            resultSet = preparedStatement.executeQuery();

            // Etape 3 : Lire les résultats
            if (resultSet.next()) {
                System.out.println("Données récupérées pour l'ID : " + id);
                int technicienId = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String experience = resultSet.getString("experience");
                String specialite = resultSet.getString("specialite");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                // Affichage des données récupérées
                System.out.println("ID : " + technicienId);
                System.out.println("Nom : " + nom);
                System.out.println("Prénom : " + prenom);
                System.out.println("Expérience : " + experience);
                System.out.println("Spécialité : " + specialite);
                System.out.println("Email : " + email);
                System.out.println("Mot de passe : " + password);

                // Instanciation de l'objet Technicien
                technicien = new Technicien(
                    technicienId, nom, prenom, experience, specialite, email, password, false
                );
            } else {
                System.out.println("Aucun technicien trouvé pour l'ID : " + id);
            }
        } catch (SQLException e) {
            // Afficher les erreurs SQL
            System.err.println("Erreur SQL : " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Fermer les ressources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connexion != null) connexion.close();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture des ressources : " + e.getMessage());
                e.printStackTrace();
            }
        }

        return technicien;
    }

}
