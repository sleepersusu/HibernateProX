package bistro.campaignServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import java.io.IOException;
import java.util.List;

import org.hibernate.SessionFactory;

import bistro.bean.CampaignBean;
import bistro.service.CampaignService;
import bistro.util.HibernateUtil;


@WebServlet("/TestShowAllCampaignServlet.do")
public class ShowAllCampaignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}


	private void processAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		CampaignService service = new CampaignService(session);
		
		List<CampaignBean> allCampaign = service.findAllCampaign();
		
		request.setAttribute("allCampaign", allCampaign);
		
//		request.getRequestDispatcher("TestShowCampaign.jsp").forward(request, response);
		request.getRequestDispatcher("ShowAllCampaign.jsp").forward(request, response);
		
	}
	
	

}
