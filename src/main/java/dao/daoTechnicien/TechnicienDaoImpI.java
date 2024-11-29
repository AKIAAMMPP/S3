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
            String sql = "INSERT INTO techniciens(nom, prenom, experience, specialite, email, password) VALUES(?, ?, ?, ?, ?, ?)";
            preparedStatement = connexion.prepareStatement(sql);

            // Assurez-vous que vous utilisez les bons indices pour les paramètres
            preparedStatement.setString(1, t.getNom());           // nom
            preparedStatement.setString(2, t.getPrenom());        // prenom
            preparedStatement.setString(3, t.getExperience());    // experience
            preparedStatement.setString(4, t.getSpecialite());    // specialite
            preparedStatement.setString(5, t.getEmail());         // email
            preparedStatement.setString(6, t.getPassword());      // password

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

                techniciens.add(new Technicien(id, nom, prenom, specialite, experience, email, password));
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

    @Override
    public Technicien getTechnicienById(int id) {
        Technicien technicien = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM techniciens WHERE id = ?";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                technicien = new Technicien(
                		resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("experience"),
                        resultSet.getString("specialite"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
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

        return technicien;
    }
}
