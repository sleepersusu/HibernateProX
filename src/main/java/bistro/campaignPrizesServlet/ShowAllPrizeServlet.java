package bistro.campaignPrizesServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.bean.CampaignPrizesBean;
import bistro.service.CampaignPrizesService;
import bistro.util.HibernateUtil;




@WebServlet("/ShowAllPrizeServlet.do")
public class ShowAllPrizeServlet extends HttpServlet {
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
		
		CampaignPrizesService service = new CampaignPrizesService(session);
		List<CampaignPrizesBean> allPriizes = service.findAllCampaignPrizes();
		
		request.setAttribute("allPriizes", allPriizes);
		
		request.getRequestDispatcher("ShowAllPrize.jsp").forward(request, response);
		
	}
}
