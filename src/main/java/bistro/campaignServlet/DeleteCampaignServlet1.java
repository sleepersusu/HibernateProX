package bistro.campaignServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.bean.CampaignBean;
import bistro.service.CampaignService;
import bistro.util.HibernateUtil;


@WebServlet("/DeleteCampaignServlet1.do")
public class DeleteCampaignServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}


	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int campaignId = Integer.parseInt(request.getParameter("campaignId"));
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		CampaignService service = new CampaignService(session); 
		CampaignBean deleteBean = service.findCampaignById(campaignId);
		 boolean isDelete = service.deleteCampaign(deleteBean);
		 if(isDelete) {
			 response.sendRedirect(request.getContextPath() + "/TestShowAllCampaignServlet.do");
		 }else {
			 request.setAttribute("errorMessage", "刪除活動失敗");
			 request.getRequestDispatcher("/TestShowAllCampaignServlet.do").forward(request, response);
		 }
		 
	}

}
