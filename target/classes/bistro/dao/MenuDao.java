package bistro.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import bistro.bean.MenuBean;

public class MenuDao {

	private Session session;

	public MenuDao(Session session) {
		this.session = session;
	}

	public List<MenuBean> queryAllMenu() {

		return session.createQuery("from MenuBean", MenuBean.class).list();

	}

	public MenuBean queryMenuByID(Integer ID) {
		return session.get(MenuBean.class, ID);
	}

	public MenuBean queryMenuByName(String name) {
		return session.get(MenuBean.class, name);
	}

	public List<MenuBean> queryMenuByNameLikeWhat(String name) {
		String hql = "from MenuBean where productname like :name";
		Query<MenuBean> query = session.createQuery(hql, MenuBean.class);
		query.setParameter("name", "%" + name + "%");
		List<MenuBean> resultList = query.list();

		return resultList;
	}

	public boolean createMenu(MenuBean saveBean) {

		session.persist(saveBean);
		return true;

	}

	public boolean deleteMenu(MenuBean deleteBean) {
		session.remove(deleteBean);
		return true;
	}

	public boolean updateMenu(MenuBean updateBean) {

		session.merge(updateBean);
		return true;

	}

}
