package bistro.service;

import org.hibernate.Session;

import bistro.bean.UsersBean;
import bistro.dao.UsersDao;


public class UserService {
	
	UsersDao dao;

	public UserService(Session session) {
		dao = new UsersDao(session);
	}
	
	public boolean loginVerify(UsersBean user) {
		return dao.loginVerify(user);
	}
	
	public boolean createUser(UsersBean user) {
		return dao.createUser(user);
    }

}
