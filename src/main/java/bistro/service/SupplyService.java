package bistro.service;

import java.util.List;

import org.hibernate.Session;

import bistro.bean.SupplyBean;
import bistro.dao.SupplyDao;

public class SupplyService {
    
	private SupplyDao dao;

    public SupplyService(Session session) {
        dao = new SupplyDao(session);
    }

    public SupplyBean findSupplyById(int id) {
        return dao.findSupplyById(id);
    }

    public List<SupplyBean> findAllSupplies() {
        return dao.findAllSupplies();
    }

    public boolean createSupply(SupplyBean supply) {
        return dao.createSupply(supply);
    }

    public boolean updateSupply(SupplyBean supply) {
        return dao.updateSupply(supply);
    }

    public boolean deleteSupply(SupplyBean supply) {
        return dao.deleteSupply(supply);
    }
}
