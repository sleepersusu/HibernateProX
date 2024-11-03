package bistro.dao;

import bistro.bean.MembersBean;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;


public class MembersDao{
	
	private Session session;
	
	public MembersDao(Session session) {
		this.session = session;
	}

	public MembersBean findMembersById(int id) {
		return session.get(MembersBean.class, id);
	}
	
	public List<MembersBean> findAllMembers(){
		String hql="FROM MembersBean AS m JOIN FETCH m.MembersDetailBean";
		return session.createQuery(hql, MembersBean.class).list();
	}
	
    public boolean createMember(MembersBean membersBean) {
        try {
            session.persist(membersBean);
            return true;
        } catch (Exception e) {
            e.printStackTrace(); // 錯誤處理
            return false;
        }
    }
	
    public boolean updateMember(MembersBean membersBean) {
        try {
            session.merge(membersBean);
            return true;
        } catch (Exception e) {
            e.printStackTrace(); // 錯誤處理
            return false;
        }
    }
	
    public boolean deleteMember(MembersBean membersBean) {
        try {
            session.remove(membersBean);
            return true;
        } catch (Exception e) {
            e.printStackTrace(); // 錯誤處理
            return false;
        }
    }
}

	