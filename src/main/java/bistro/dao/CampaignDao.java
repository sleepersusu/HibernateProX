package bistro.dao;

import bistro.bean.CampaignBean;

import java.util.List;


import org.hibernate.Session;


public class CampaignDao{
	
	private Session session;
	
	
	public CampaignDao(Session session) {
		this.session = session;
	}

	
	
	public CampaignBean findCampaignById(int id) {
		return session.get(CampaignBean.class, id);
	}
	
	public List<CampaignBean> findAllCampaign(){
		return session.createQuery("from CampaignBean", CampaignBean.class).list();
	}
	
	public boolean createCampaign(CampaignBean campaign) {
		session.persist(campaign);
		return true;
	}
	
	public boolean updateCampaign(CampaignBean campaignBean) {
		session.merge(campaignBean);
		return true;
	}
	
	public boolean deleteCampaign(CampaignBean campaignBean) {
		session.remove(campaignBean);
		return true;
	}
}

	