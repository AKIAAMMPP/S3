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
	
	/* Une fonction qui parcourt les trois tables des utilisateurs possibles et retourne l'entite user qui contient 
	juste le username et son type */
	
	public User authenticate(String email, String password) {
        String sql = 
            "SELECT email, password, 'client' AS type_user FROM clients WHERE email = ? AND password = ? " +
            "UNION " +
            "SELECT email, password, 'admin' AS type_user FROM admin WHERE email = ? AND password = ?"+
            "UNION " +
            "SELECT email, password, 'technicien' AS type_user FROM techniciens WHERE email = ? AND password = ?";
        
        try (
        	Connection	conn = daoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, email);
            ps.setString(6, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String typeUser = rs.getString("type_user");
                User user = new User(email,typeUser);
                return user  ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
