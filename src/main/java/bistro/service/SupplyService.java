package bistro.service;



import java.util.List;


import org.hibernate.Session;

import bistro.bean.CampaignBean;
import bistro.bean.SupplyBean;
import bistro.dao.CampaignDao;
import bistro.dao.SupplyDao;


public class SupplyService{
	
	private SupplyDao  dao;
	
	
	public SupplyService(Session session) {
		dao = new SupplyDao(session);
	}

	
	
	public SupplyBean findSupplyById(int id) {
		return dao.findSupplyById(id);
	}
	
	public List<SupplyBean> findAllSupply(){
		return dao.findAllSupply();
	}
	
	public boolean createSupply(SupplyBean supplyBean) {		
		return dao.createSupply(supplyBean);
	}
	
	public boolean updateSupply(SupplyBean supplyBean) {
		return dao.updateSupply(supplyBean);
	}
	
	public boolean deleteSupply(SupplyBean supplyBean) {
		return dao.deleteSupply(supplyBean);
	}
}

	