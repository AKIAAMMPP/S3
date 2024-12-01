package dao.daoTechnicien;

import java.util.List;
import beans.Technicien;

public interface TechnicienDAO {
    
    void createTechnicien(Technicien t);
    Technicien getTechnicienById(int id);
    List<Technicien> getAllTechniciens();
    void updateTechnicien(Technicien t);
    void deleteTechnicien(int id);
	boolean updateDisponibilite(int technicienId, boolean disponibilite);
}
