package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.User;



public class DAOAuth {
    private DAOFactory daoFactory;
    
    public DAOAuth(DAOFactory daoFactory) {
        super();
        this.daoFactory = daoFactory;
    }
    
    /* Une fonction qui parcourt les trois tables des utilisateurs possibles et retourne
       l'entité user qui contient l'ID, l'email, le mot de passe et le type d'utilisateur */
    
    public User authenticate(String email, String password) {
        String sql = 
            "SELECT id, email, password, 'client' AS type_user FROM clients WHERE email = ? AND password = ? " +
            "UNION " +
            "SELECT id, email, password, 'admin' AS type_user FROM admin WHERE email = ? AND password = ? " +
            "UNION " +
            "SELECT id, email, password, 'technicien' AS type_user FROM techniciens WHERE email = ? AND password = ?";
        
        try (
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            // Paramètres de la requête SQL
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, email);
            ps.setString(6, password);

            // Exécution de la requête
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");  // Récupérer l'ID de l'utilisateur
                String typeUser = rs.getString("type_user");  // Récupérer le type d'utilisateur
                
                // Créer un objet User avec ID, email et type d'utilisateur
                User user = new User(id, email, typeUser);
                return user;  // Retourner l'objet User
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;  // Si aucun utilisateur n'est trouvé, retourner null
    }
}
