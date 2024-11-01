package bistro.dao;

import bistro.bean.CampaignBean;
import bistro.bean.SupplyBean;

import java.util.List;


import org.hibernate.Session;


public class SupplyDao{
	
	private Session session;
	
	
	public SupplyDao(Session session) {
		this.session = session;
	}

	
	
	public SupplyBean findSupplyById(int id) {
		return session.get(SupplyBean.class, id);
	}
	
	public List<SupplyBean> findAllSupply(){
		return session.createQuery("from SupplyBean", SupplyBean.class).list();
	}
	
	public boolean createSupply(SupplyBean supplyBean) {
		session.persist(supplyBean);
		return true;
	}
	
	public boolean updateSupply(SupplyBean supplyBean) {
		session.merge(supplyBean);
		return true;
	}
	
	public boolean deleteSupply(SupplyBean supplyBean) {
		session.remove(supplyBean);
		return true;
	}

	

	



}

	