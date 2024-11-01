package bistro.actionTest;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.bean.CampaignBean;
import bistro.bean.CampaignPrizesBean;
import bistro.bean.SupplyBean;
import bistro.bean.SupplyOriBean;
import bistro.service.CampaignPrizesService;
import bistro.service.SupplyService;
import bistro.util.HibernateUtil;

public class ServiceActionTestForSupply {


	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		
		SupplyService service = new SupplyService(session);
		List<SupplyBean> allSupply = service.findAllSupply();
		
		for(SupplyBean supply: allSupply) {
			System.out.println(supply.getSupply_id() + " " 
								+ supply.getSupply_product() + " " 
								+ supply.getSupply_count() + " " 
								+ supply.getSupply_price() + " " 
								+ supply.getCreated_at());
			
			
			
			List<SupplyOriBean> supplyOriBean = supply.getSupplyOriBean();
			System.out.println(supplyOriBean. + " " + campaignBean.getCampaign_title());
		}

	}

}
