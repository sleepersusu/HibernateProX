package bistro.dao;

import java.util.List;

import org.hibernate.Session;

import bistro.bean.PointPrizesBean;

public class PointPrizesDao {
	private Session session;
	
    public PointPrizesDao(Session session) {
        this.session = session;
    }
    
    public List<PointPrizesBean> findAllPointPrizes(){
    	return session.createQuery("FROM PointPrizesBean", PointPrizesBean.class).list();
    }
    
    public boolean createPointPrizes(PointPrizesBean pointPrizes) {
        session.persist(pointPrizes);
        return true;
    }

    
    public boolean updatePointPrizes(PointPrizesBean pointPrizes) {
        session.merge(pointPrizes);
        return true;
    }

    
    public boolean deletePointPrizes(PointPrizesBean pointPrizes) {
        session.remove(pointPrizes);
        return true;
    }
    
    
}
