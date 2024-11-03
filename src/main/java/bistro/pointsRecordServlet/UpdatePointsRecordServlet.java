package bistro.pointsRecordServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

@WebServlet("/UpdatePointsRecordServlet.do")
public class UpdatePointsRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter();

	    int PointsRecords_id = Integer.parseInt(request.getParameter("pointsRecordsId"));
	    int members_id = Integer.parseInt(request.getParameter("memberId"));
	    int prize_id = Integer.parseInt(request.getParameter("prizeId"));
	    String records_date = request.getParameter("recordsDate");

	    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	    try (Session session = sessionFactory.openSession()) {
	        session.beginTransaction();

	        PointsRecordBean pointsRecordBean = session.get(PointsRecordBean.class, PointsRecords_id);
	        MembersDetailBean memberDetail = session.get(MembersDetailBean.class, members_id);
	        PointPrizesBean pointPrize = session.get(PointPrizesBean.class, prize_id);
	        pointsRecordBean.setMembersDetail(memberDetail);
	        pointsRecordBean.setPointPrizes(pointPrize);
	        try {
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            Date expirationDate = dateFormat.parse(records_date);
	            pointsRecordBean.setRecords_date(expirationDate);
	        } catch (ParseException e) {
	            out.println("日期格式解析錯誤: " + e.getMessage());
	            e.printStackTrace(out); // 输出详细错误信息
	        }

	                session.update(pointsRecordBean);
	                session.getTransaction().commit();
	                
	                response.sendRedirect(request.getContextPath() + "/ShowAllPointsRecordServlet.do");

	    out.close();
	}
	}
}
