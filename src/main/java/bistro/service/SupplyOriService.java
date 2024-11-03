package bistro.service;

import java.util.List;

import org.hibernate.Session;

import bistro.bean.SupplyOriBean;
import bistro.dao.SupplyOriDao;

public class SupplyOriService {
    
	private SupplyOriDao dao;

    public SupplyOriService(Session session) {
        dao = new SupplyOriDao(session);
    }

    public SupplyOriBean findSupplyOriById(int id) {
        return dao.findSupplyOriById(id);
    }

    public List<SupplyOriBean> findAllSupplyOris() {
        return dao.findAllSupplyOris();
    }

    public boolean createSupplyOri(SupplyOriBean supplyOri) {
        return dao.createSupplyOri(supplyOri);
    }

    public boolean updateSupplyOri(SupplyOriBean supplyOri) {
        return dao.updateSupplyOri(supplyOri);
    }

    public boolean deleteSupplyOri(SupplyOriBean supplyOri) {
        return dao.deleteSupplyOri(supplyOri);
    }
}
