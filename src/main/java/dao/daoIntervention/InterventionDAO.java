package dao.daoIntervention;

import java.util.List;

import beans.Intervention;

public interface InterventionDAO {
    List<Intervention> getAllIntervention();
    void updateIntervention(Intervention s);
    void deleteIntervention(int id);
    Intervention getInterventionById(int id);
    void createIntervention(Intervention s);

}
