package bistro.service;

import bistro.bean.LotteryEntriesBean;
import bistro.dao.LotteryEntriesDao;

import java.util.List;

import org.hibernate.Session;

public class LotteryEntriesService {
	
	LotteryEntriesDao dao;

	public LotteryEntriesService(Session session) {
		dao = new LotteryEntriesDao(session);
	}
	
	public List<LotteryEntriesBean> findAllEntries(){
		return dao.findAllEntries();
	}
	
	public boolean insertLotteryEntry(LotteryEntriesBean bean) {
		return dao.insertLotteryEntry(bean);
	}
	
	public LotteryEntriesBean findEntryById(int id) {
		return dao.findEntryById(id);
	}
	
	public boolean deleteLotteryEntryById(int id) {
		return dao.deleteLotteryEntryById(id);
	}

}
