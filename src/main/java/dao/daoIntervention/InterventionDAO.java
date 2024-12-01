package dao.daoIntervention;

import java.sql.SQLException;
import java.util.List;

import beans.Intervention;
import beans.Service;

public interface InterventionDAO {
    List<Intervention> getAllIntervention() throws SQLException;
    void updateIntervention(Intervention s);
    void deleteIntervention(int id);
    Intervention getInterventionById(int id);
    void createIntervention(Intervention s);

}
