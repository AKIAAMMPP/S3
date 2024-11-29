package dao.daoClient;

import java.sql.SQLException;
import java.util.List;

import beans.Client;
import beans.Service;

public interface ClientDAO {
	List<Client> getAllClients(); // Méthode pour mettre à jour un client 
	void updateClient(Client c); // Méthode pour supprimer un client par son ID 
	void deleteClient(int id); // Méthode pour obtenir un client par son ID 
	Client getClientById(int id);
	void createClient(Client client);
	
}
