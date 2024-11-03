package bistro.pointPrizesServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.bean.PointPrizesBean;
import bistro.util.HibernateUtil;

@WebServlet("/DeletePointPrizesServlet.do")
public class DeletePointPrizesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");

		// 取得要刪除的 PointPrizes ID
		int pointPrizes_id = Integer.parseInt(request.getParameter("recordsId"));

		// 使用 Hibernate session 進行刪除操作
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();

			// 查找要刪除的 PointPrizesBean
			PointPrizesBean pointPrizes = session.get(PointPrizesBean.class, pointPrizes_id);
			if (pointPrizes != null) {
				// 刪除該記錄
				session.delete(pointPrizes);
				session.getTransaction().commit();
				// 刪除成功後重新導向
				response.sendRedirect(request.getContextPath() + "/ShowAllPointPrizesServlet.do");
			} else {
				// 如果找不到記錄，顯示提示信息
				response.getWriter().println("Record not found.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("An error occurred while deleting the record.");
		}
	}
}
