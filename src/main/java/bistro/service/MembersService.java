package bistro.service;

import java.util.List;
import org.hibernate.Session;
import bistro.bean.MembersBean;
import bistro.bean.MembersDetailBean;
import bistro.dao.MembersDao;

public class MembersService{
	
	private MembersDao memberDao;
	
	public MembersService(Session session) {
		memberDao = new MembersDao(session);
	}

	public MembersBean findMembersById(Integer id) {
		return memberDao.findMembersById(id);
	}
	
	public List<MembersBean> findAllMembers(){
		List<MembersBean> allMembers = memberDao.findAllMembers();
		String UserSex=null;
		String userFavor=null;
		for (MembersBean membersBean : allMembers) {
			MembersDetailBean membersDetailBean = membersBean.getMembersDetailBean();
			Short dateFavor = membersDetailBean.getMembersD_favor();
			Short dateSex = membersDetailBean.getMembersD_sex();
			membersDetailBean.setUserSexStr(dateSex!=0 ? "男":"女");
			membersDetailBean.setUserFavorStr(dateFavor!=0 ? "外向":"內向");
		}

		return allMembers;
	}
	
	public boolean createMembers(MembersBean membersBean) {		
		return memberDao.createMember(membersBean);
	}
	
	public boolean updateMembers(MembersBean membersBeanBean) {
		return memberDao.updateMember(membersBeanBean);
	}
	
	public boolean deleteMembers(Integer userid) {
		MembersBean membersById = this.findMembersById(userid);
		return memberDao.deleteMember(membersById);
	}
}

	