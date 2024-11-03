package bistro.service;

import java.util.List;

import org.hibernate.Session;

import bistro.bean.EmployeeBean;
import bistro.dao.EmployeeDao;

public class EmployeeService {
    
	private EmployeeDao dao;

    public EmployeeService(Session session) {
        dao = new EmployeeDao(session);
    }

    public EmployeeBean findEmployeeById(int id) {
        return dao.findEmployeeById(id);
    }

    public List<EmployeeBean> findAllEmployees() {
        return dao.findAllEmployees();
    }

    public boolean createEmployee(EmployeeBean employee) {
        return dao.createEmployee(employee);
    }

    public boolean updateEmployee(EmployeeBean employee) {
        return dao.updateEmployee(employee);
    }

    public boolean deleteEmployee(EmployeeBean employee) {
        return dao.deleteEmployee(employee);
    }
}
