package bistro.dao;

import bistro.bean.SupplyOriBean;
import org.hibernate.Session;

import java.util.List;

public class SupplyOriDao {
    private Session session;

    public SupplyOriDao(Session session) {
        this.session = session;
    }

    public SupplyOriBean findSupplyOriById(int id) {
        return session.get(SupplyOriBean.class, id);
    }

    public List<SupplyOriBean> findAllSupplyOris() {
        return session.createQuery("from SupplyOriBean", SupplyOriBean.class).list();
    }

    public boolean createSupplyOri(SupplyOriBean supplyOri) {
        session.persist(supplyOri);
        return true;
    }

    public boolean updateSupplyOri(SupplyOriBean supplyOri) {
        session.merge(supplyOri);
        return true;
    }

    public boolean deleteSupplyOri(SupplyOriBean supplyOri) {
        session.remove(supplyOri);
        return true;
    }
}
