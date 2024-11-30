package dao.daoDemande;

import java.sql.SQLException;
import java.util.List;

import beans.Demande;

public interface DemandeDAO {
	    List<Demande> getAllDemandes() throws SQLException;
	    void updateDemande(Demande s);
	    void deleteDemande(int id);
	    Demande getDemandeById(int id);
	    void createDemande(Demande s);

}
