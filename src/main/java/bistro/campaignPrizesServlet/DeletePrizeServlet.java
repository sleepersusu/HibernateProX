package bistro.campaignPrizesServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.service.CampaignPrizesService;
import bistro.util.HibernateUtil;


@WebServlet("/DeletePrizeServlet.do")
public class DeletePrizeServlet extends HttpServlet {
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
		int prizeId = Integer.parseInt(request.getParameter("prizeId"));
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		CampaignPrizesService service = new CampaignPrizesService(session);
		boolean isDelete = service.deleteCampaignPrize(service.findCampaignPrizeById(prizeId));
				
		if(isDelete) {
			response.sendRedirect(request.getContextPath() + "/ShowAllPrizeServlet.do");
		}else {
			out.println("刪除失敗...");
		}
		
		out.close();
		
	}

}
