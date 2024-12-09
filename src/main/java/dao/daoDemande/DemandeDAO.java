package dao.daoDemande;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import beans.Demande;

public interface DemandeDAO {
	    List<Demande> getAllDemandes() throws SQLException;
	    List<Demande> getAllDemandes_pour_intervention() throws SQLException;
	    List<Demande> getDemandesByClientId(int id);
	    void updateDemande(Demande s);
	    void deleteDemande(int id);
	    Demande getDemandeById(int id);
	    void createDemande(Demande s);
		void updateDemandeStatut(int demandeId, String newStatut);
		List<Demande> getDemandesWithServiceNamesByClientId(Integer clientId);
		Map<String, Integer> getDemandeStats();

}
