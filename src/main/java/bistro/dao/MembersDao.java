package bistro.dao;

import bistro.bean.MembersBean;

import java.util.List;


import org.hibernate.Session;


public class MembersDao{
	
	private Session session;
	
	
	public MembersDao(Session session) {
		this.session = session;
	}

	
	
	public MembersBean findMembersById(int id) {
		return session.get(MembersBean.class, id);
	}
	
	public List<MembersBean> findAllMembers(){
		return session.createQuery("from CampaignBean", MembersBean.class).list();
	}
	
	public boolean createCampaign(MembersBean membersBean) {
		session.persist(membersBean);
		return true;
	}
	
	public boolean updateCampaign(MembersBean membersBean) {
		session.merge(membersBean);
		return true;
	}
	
	public boolean deleteCampaign(MembersBean membersBean) {
		session.remove(membersBean);
		return true;
	}
}

	