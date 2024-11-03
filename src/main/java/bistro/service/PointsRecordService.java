package bistro.service;

import java.util.List;

import org.hibernate.Session;

import bistro.bean.PointsRecordBean;
import bistro.dao.CampaignPrizesDao;
import bistro.dao.PointsRecordDao;

public class PointsRecordService {

	PointsRecordDao  dao;
	
	public PointsRecordService(Session session) {
		dao = new PointsRecordDao(session);
	}
	
	public List<PointsRecordBean>findAllPointsRecord(){
		return dao.findAllPointsRecord();
	}
	
	public boolean createPointsRecord(PointsRecordBean pointsRecordBean) {
		return dao.createPointsRecord(pointsRecordBean);
	}
	
	public boolean updatePointsRecord(PointsRecordBean pointsRecordBean) {
		return dao.updatePointsRecord(pointsRecordBean);
	}
	
	public boolean deletePointsRecord(PointsRecordBean pointsRecordBean) {
		return dao.deletePointsRecord(pointsRecordBean);
	}
}
