package bistro.pointsRecordServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.bean.CampaignBean;
import bistro.bean.CampaignPrizesBean;
import bistro.bean.MembersDetailBean;
import bistro.bean.PointPrizesBean;
import bistro.bean.PointsRecordBean;
import bistro.service.CampaignPrizesService;
import bistro.util.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CreatePointsRecordServlet.do")
public class CreatePointsRecordServlet extends HttpServlet{
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

	    int members_id = Integer.parseInt(request.getParameter("memberId"));
	    int prize_id = Integer.parseInt(request.getParameter("prizeId"));
	    String records_date = request.getParameter("recordsDate");

	    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	    try (Session session = sessionFactory.openSession()) {
	        session.beginTransaction();
	        
	        // 根據 ID 獲取 MembersDetailBean 和 PointPrizesBean 對象
	        MembersDetailBean memberDetail = session.get(MembersDetailBean.class, members_id);
	        PointPrizesBean pointPrize = session.get(PointPrizesBean.class, prize_id);
	        
	        if (memberDetail != null && pointPrize != null) {
	            PointsRecordBean pointsRecordBean = new PointsRecordBean();
	            pointsRecordBean.setMembersDetail(memberDetail);
	            pointsRecordBean.setPointPrizes(pointPrize);
	            
	            try {
	                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	                Date expirationDate = dateFormat.parse(records_date);
	                pointsRecordBean.setRecords_date(expirationDate);
	            } catch (ParseException e) {
	                e.printStackTrace();
	            }
	            
	            session.persist(pointsRecordBean);
	            session.getTransaction().commit();
	            response.sendRedirect(request.getContextPath() + "/ShowAllPointsRecordServlet.do");
	        } else {
	            out.println("Member or Prize not found.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    out.close();
	}
}
