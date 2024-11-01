package bistro.service;

import java.util.List;

import org.hibernate.Session;

import bistro.bean.MenuBean;
import bistro.dao.MenuDao;

public class MenuService {

	MenuDao dao;

	public MenuService(Session session) {
		dao = new MenuDao(session);
	}

	public List<MenuBean> queryAllMenu() {

		return dao.queryAllMenu();

	}

	public MenuBean queryMenuByID(Integer ID) {
		return dao.queryMenuByID(ID);
	}

	public MenuBean queryMenuByName(String name) {
		return dao.queryMenuByName(name);
	}

	public List<MenuBean> queryMenuByNameLikeWhat(String name) {
		return dao.queryMenuByNameLikeWhat(name);
	}

	public boolean createMenu(MenuBean saveBean) {

		return dao.createMenu(saveBean);

	}

	public boolean deleteMenu(MenuBean deleteBean) {
		return dao.deleteMenu(deleteBean);
	}

	public boolean updateMenu(MenuBean updateBean) {

		return dao.updateMenu(updateBean);

	}

}
