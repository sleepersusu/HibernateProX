package bistro.dao;

import bistro.bean.EmployeeBean;
import org.hibernate.Session;

import java.util.List;

public class EmployeeDao {
    private Session session;

    public EmployeeDao(Session session) {
        this.session = session;
    }

    public EmployeeBean findEmployeeById(int id) {
        return session.get(EmployeeBean.class, id);
    }

    public List<EmployeeBean> findAllEmployees() {
        return session.createQuery("from EmployeeBean", EmployeeBean.class).list();
    }

    public boolean createEmployee(EmployeeBean employee) {
        session.persist(employee);
        return true;
    }

    public boolean updateEmployee(EmployeeBean employee) {
        session.merge(employee);
        return true;
    }

    public boolean deleteEmployee(EmployeeBean employee) {
        session.remove(employee);
        return true;
    }
}
