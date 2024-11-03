package bistro.pointPrizesServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.bean.MembersDetailBean;
import bistro.bean.PointPrizesBean;
import bistro.bean.PointsRecordBean;
import bistro.util.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdatePointPrizesServlet.do")
public class UpdatePointPrizesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		int rewardsId = Integer.parseInt(request.getParameter("rewardsId"));
		String rewardsName = request.getParameter("rewardsName");
		int rewardsPoints = Integer.parseInt(request.getParameter("rewardsPoints"));
		String rewardsDescription = request.getParameter("rewardsDescription");
		String rewardsDate = request.getParameter("rewardsDate");

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();

			PointPrizesBean PointPrizesBean = new PointPrizesBean();
			PointPrizesBean.setPointPrizes_id(rewardsId);
			PointPrizesBean.setPointPrizes_name(rewardsName);
			PointPrizesBean.setPointPrizes_points(rewardsPoints);
			PointPrizesBean.setPointPrizes_description(rewardsDescription);

			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date expirationDate = dateFormat.parse(rewardsDate);
				PointPrizesBean.setPointPrizes_expiration(expirationDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			session.update(PointPrizesBean);
			session.getTransaction().commit();
			response.sendRedirect(request.getContextPath() + "/ShowAllPointPrizesServlet.do");

			out.close();
		}
	}
}