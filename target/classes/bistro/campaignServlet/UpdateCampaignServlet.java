package bistro.campaignServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.bean.CampaignBean;
import bistro.service.CampaignService;
import bistro.util.HibernateUtil;

@WebServlet("/UpdateCampaignServlet.do")
public class UpdateCampaignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processAction(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processAction(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void processAction(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
		int campaignId = Integer.parseInt(request.getParameter("campaignId"));
		String campaignTitle = request.getParameter("campaignTitle");
		String campaignDescription = request.getParameter("campaignDescription");
		String campaignType = request.getParameter("campaignType");
		String campaignStartDate = request.getParameter("campaignStartDate");
		String campaignEndDate = request.getParameter("campaignEndDate");
		String note = request.getParameter("note");
		Timestamp createAt = Timestamp.valueOf(request.getParameter("createAt"));
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		CampaignService service = new CampaignService(session);
		CampaignBean bean = new CampaignBean();
		bean.setCampaign_id(campaignId);
		bean.setCampaign_title(campaignTitle);
		bean.setCampaign_description(campaignDescription);
		bean.setCampaign_type(campaignType);
		bean.setCampaign_start_date(campaignStartDate);
		bean.setEnd_date(campaignEndDate);
		bean.setNote(note);
		bean.setCreated_at(createAt);
		
		boolean isUpdate = service.updateCampaign(bean);
		
		if(isUpdate) {
			response.sendRedirect(request.getContextPath() + "/TestShowAllCampaignServlet.do");
		}
		
		
	}

}
