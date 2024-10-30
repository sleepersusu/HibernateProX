package bistro.service;



import java.util.List;


import org.hibernate.Session;

import bistro.bean.CampaignBean;
import bistro.dao.CampaignDao;


public class CampaignService{
	
	private CampaignDao  dao;
	
	
	public CampaignService(Session session) {
		dao = new CampaignDao(session);
	}

	
	
	public CampaignBean findCampaignById(int id) {
		return dao.findCampaignById(id);
	}
	
	public List<CampaignBean> findAllCampaign(){
		return dao.findAllCampaign();
	}
	
	public boolean createCampaign(CampaignBean campaign) {		
		return dao.createCampaign(campaign);
	}
	
	public boolean updateCampaign(CampaignBean campaignBean) {
		return dao.updateCampaign(campaignBean);
	}
	
	public boolean deleteCampaign(CampaignBean campaignBean) {
		return dao.deleteCampaign(campaignBean);
	}
}

	