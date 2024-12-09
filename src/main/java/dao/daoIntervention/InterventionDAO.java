package dao.daoIntervention;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import beans.Intervention;

public interface InterventionDAO {
    List<Intervention> getAllIntervention();
    List<Intervention> getInterventionsByDemandeId(int id);
    List<Intervention> getInterventions_admin_ByDemandeId(int id);
    void updateIntervention(Intervention s);
    void updateIntervention_note_nom(Intervention s);
    void deleteIntervention(int id);
    Intervention getInterventionById(int id);
    void createIntervention(Intervention s);
	int getInterventionsEnCoursCount();
	List<Map<String, Object>> getInterventionsTermineeParTechnicien(int technicienId) throws SQLException;
	List<Map<String, Object>> getInterventionsEnCoursParTechnicien(int technicienId) throws SQLException;
	boolean updateeStatus(int interventionId, String newStatus);
	List<Intervention> getInterventionsByClientId(Integer clientId);
	List<Intervention> getCompletedInterventionsByTechnicien(Integer userId);
	List<Map<String, Object>> getInterventionsTerminee_ParTechnicien(Integer technicienId) throws SQLException;

}
