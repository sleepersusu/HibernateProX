package bistro.service;

import java.util.List;

import org.hibernate.Session;

import bistro.bean.PointPrizesBean;
import bistro.dao.PointPrizesDao;

public class PointPrizesService {
	PointPrizesDao dao;

	public PointPrizesService(Session session) {
		dao = new PointPrizesDao(session);
	}
		
	public List<PointPrizesBean> findAllPointPrizes() {
		return dao.findAllPointPrizes();
	}
	
	public boolean createPointPrizes(PointPrizesBean pointPrizes) {
		return dao.createPointPrizes(pointPrizes);
	}
	
	public boolean updatePointPrizes(PointPrizesBean pointPrizes) {
		return dao.updatePointPrizes(pointPrizes);
	}
	
	public boolean deletePointPrizes(PointPrizesBean pointPrizes) {
        return dao.deletePointPrizes(pointPrizes);
    }
}
