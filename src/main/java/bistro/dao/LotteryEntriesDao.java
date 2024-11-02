package bistro.dao;

import java.util.List;

import org.hibernate.Session;

import bistro.bean.LotteryEntriesBean;


public class LotteryEntriesDao {
	
	private Session session;

	public LotteryEntriesDao(Session session) {
		this.session = session;
	}
	
	public List<LotteryEntriesBean> findAllEntries(){
		return session.createQuery("from LotteryEntriesBean", LotteryEntriesBean.class).list();
	}
	
	public boolean insertLotteryEntry(LotteryEntriesBean bean) {
		if(bean != null) {
			session.persist(bean);
			return true;
		}
		
		return false;
	}
	
	public LotteryEntriesBean findEntryById(int id) {
		return session.get(LotteryEntriesBean.class, id);
	}
	
	public boolean deleteLotteryEntryById(int id) {
		LotteryEntriesBean deleteBean = session.get(LotteryEntriesBean.class, id);
		if(deleteBean != null) {
			session.remove(deleteBean);
			return true;
		}
		return false;
	}

}
