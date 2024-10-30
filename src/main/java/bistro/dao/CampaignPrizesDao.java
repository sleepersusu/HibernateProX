package bistro.dao;

import bistro.bean.CampaignPrizesBean;
import org.hibernate.Session;

import java.util.List;

public class CampaignPrizesDao {

    private Session session;

    public CampaignPrizesDao(Session session) {
        this.session = session;
    }

    
    public CampaignPrizesBean findCampaignPrizeById(int id) {
        return session.get(CampaignPrizesBean.class, id);
    }

   
    public List<CampaignPrizesBean> findAllCampaignPrizes() {
        return session.createQuery("FROM CampaignPrizesBean", CampaignPrizesBean.class).list();
    }

   
    public boolean createCampaignPrize(CampaignPrizesBean campaignPrize) {
        session.persist(campaignPrize);
        return true;
    }

    
    public boolean updateCampaignPrize(CampaignPrizesBean campaignPrize) {
        session.merge(campaignPrize);
        return true;
    }

    
    public boolean deleteCampaignPrize(CampaignPrizesBean campaignPrize) {
        session.remove(campaignPrize);
        return true;
    }
}
