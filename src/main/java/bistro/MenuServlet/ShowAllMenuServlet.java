package bistro.MenuServlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import bistro.bean.MenuBean;
import bistro.service.MenuService;
import bistro.util.HibernateUtil;


@WebServlet(name = "ShowAllMenuServlet.do", urlPatterns = { "/ShowAllMenuServlet.do" })
public class ShowAllMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);

	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		MenuService menuService = new MenuService(session);


		List<MenuBean> queryAllMenu = menuService.queryAllMenu();

		request.setAttribute("queryAllProduct", queryAllMenu);
		
		request.getRequestDispatcher("ShowAllMenu.jsp").forward(request, response);




	}

}
