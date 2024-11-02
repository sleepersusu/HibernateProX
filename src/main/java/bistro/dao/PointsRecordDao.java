package bistro.dao;

import java.util.List;

import org.hibernate.Session;

import bistro.bean.PointsRecordBean;

public class PointsRecordDao {

	private Session session;

	public PointsRecordDao(Session session) {
		this.session = session;
	}

	public List<PointsRecordBean> findAllPointsRecord(){
		return session.createQuery("from PointsRecordBean", PointsRecordBean.class).list();
	}
	
	public boolean createPointsRecord(PointsRecordBean pointsRecordBean) {
		session.persist(pointsRecordBean);
		return true;
	}
	
	public boolean updatePointsRecord(PointsRecordBean pointsRecordBean) {
		session.merge(pointsRecordBean);
		return true;
	}
	
	public boolean deletePointsRecord(PointsRecordBean pointsRecordBean) {
		session.remove(pointsRecordBean);
		return true;
	}
	
}
