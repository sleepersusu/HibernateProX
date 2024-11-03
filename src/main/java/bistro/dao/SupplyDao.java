package bistro.dao;

import bistro.bean.SupplyBean;
import org.hibernate.Session;

import java.util.List;

public class SupplyDao {
    private Session session;

    public SupplyDao(Session session) {
        this.session = session;
    }

    public SupplyBean findSupplyById(int id) {
        return session.get(SupplyBean.class, id);
    }

    public List<SupplyBean> findAllSupplies() {
        return session.createQuery("from SupplyBean", SupplyBean.class).list();
    }

    public boolean createSupply(SupplyBean supply) {
        session.persist(supply);
        return true;
    }

    public boolean updateSupply(SupplyBean supply) {
        session.merge(supply);
        return true;
    }

    public boolean deleteSupply(SupplyBean supply) {
        session.remove(supply);
        return true;
    }
}
