package bistro.dao;


import org.hibernate.query.Query;

import bistro.bean.UsersBean;
import org.hibernate.Session;

public class UsersDao{
	
	private Session session;
	
	public UsersDao(Session session) {
		this.session = session;
	}
	
	
	public boolean loginVerify(UsersBean user) {
            String hql = "from UsersBean where users_account = :account and users_password = :password";
            Query<UsersBean> query = session.createQuery(hql, UsersBean.class);
            query.setParameter("account", user.getUsers_account());
            query.setParameter("password", user.getUsers_password());

            return query.uniqueResult() != null;  
        
    }
	
	public boolean isAccountUnique(String userName) {
        String hql = "FROM UsersBean WHERE users_account = :userName";
        Query<UsersBean> query = session.createQuery(hql, UsersBean.class);
        query.setParameter("userName", userName);
        return query.uniqueResult() == null; 
    }

	
	public boolean createUser(UsersBean user) {
		if(!isAccountUnique(user.getUsers_account())) {
			return false;
		}
		session.persist(user);
		return true;
    }

}
