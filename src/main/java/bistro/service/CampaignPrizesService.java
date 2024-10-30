package bistro.service;

import java.util.List;

import org.hibernate.Session;

import bistro.bean.CampaignPrizesBean;
import bistro.dao.CampaignPrizesDao;

public class CampaignPrizesService {
	
	CampaignPrizesDao dao;

	public CampaignPrizesService(Session session) {
		dao = new CampaignPrizesDao(session);
	}
	
	public CampaignPrizesBean findCampaignPrizeById(int id) {
		return dao.findCampaignPrizeById(id);
	}
	
	public List<CampaignPrizesBean> findAllCampaignPrizes() {
		return dao.findAllCampaignPrizes();
	}
	
	public boolean createCampaignPrize(CampaignPrizesBean campaignPrize) {
		return dao.createCampaignPrize(campaignPrize);
	}
	
	public boolean updateCampaignPrize(CampaignPrizesBean campaignPrize) {
		return dao.updateCampaignPrize(campaignPrize);
	}
	
	public boolean deleteCampaignPrize(CampaignPrizesBean campaignPrize) {
        return dao.deleteCampaignPrize(campaignPrize);
    }

}
