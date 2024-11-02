package bistro.actionTest;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.bean.CampaignBean;
import bistro.bean.CampaignPrizesBean;
import bistro.service.CampaignPrizesService;
import bistro.util.HibernateUtil;

public class ServiceActionTest {

		public static void main(String[] args) {
			SessionFactory factory = HibernateUtil.getSessionFactory();
			Session session = factory.openSession();
			
			CampaignPrizesService service = new CampaignPrizesService(session);
			List<CampaignPrizesBean> allPriizes = service.findAllCampaignPrizes();
			
			for(CampaignPrizesBean prize: allPriizes) {
				System.out.println(prize.getCampaignPrizes_id() + " " + prize.getCampaignPrizes_name() + " " + prize.getCampaignPrizes_description()
				 + " " + prize.getCampaignPrizes_quantity() + " " + prize.getCreated_at());
				CampaignBean campaignBean = prize.getCampaignBean();
				System.out.println(campaignBean.getCampaign_id() + " " + campaignBean.getCampaign_title());
			}

		}

	}
