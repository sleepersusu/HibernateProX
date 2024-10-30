package bistro.campaignServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.bean.CampaignBean;
import bistro.service.CampaignService;
import bistro.util.HibernateUtil;



@WebServlet("/TestCreateCampaignServlet.do")
public class CreateCampaignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processAction(request, response);
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processAction(request, response);
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String title = request.getParameter("campaignTitle");
		String description = request.getParameter("campaignDescription");
		String type = request.getParameter("campaignType");
		String startDate = request.getParameter("campaignStartDate");
		String endDate = request.getParameter("campaignEndDate");
		String note = request.getParameter("note");
		String verification = request.getParameter("verification");
		boolean membersOnly = "會員限定".equals(verification);
		int minTotalPrice = Integer.parseInt(request.getParameter("minAmount"));
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		CampaignService service = new CampaignService(session);
		CampaignBean bean = new CampaignBean();
		bean.setCampaign_title(title);
		bean.setCampaign_description(description);
		bean.setCampaign_type(type);
		bean.setCampaign_start_date(startDate);
		bean.setEnd_date(endDate);
		bean.setNote(note);
		bean.setCreated_at(new Timestamp(System.currentTimeMillis()));
		
		
		boolean campaign = service.createCampaign(bean);
		if(campaign) {
			response.sendRedirect(request.getContextPath() + "/TestShowAllCampaignServlet.do");			
		}else {
			out.println("新增活動資訊失敗...");
		}
		
		
		out.close();
		
	}

}
