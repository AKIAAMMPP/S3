package dao.daoService;

import java.sql.SQLException;
import java.util.List;
import beans.Service;

public interface ServiceDAO {
    List<Service> getAllServices() throws SQLException;
    void updateService(Service s);
    void deleteService(int id);
    Service getServiceById(int id);
    void createService(Service s);
}

		 




