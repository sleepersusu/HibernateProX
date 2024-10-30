package bistro.campaignPrizesServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.bean.CampaignBean;
import bistro.bean.CampaignPrizesBean;
import bistro.service.CampaignPrizesService;
import bistro.util.HibernateUtil;


@WebServlet("/CreatePrizeServlet.do")
public class CreatePrizeServlet extends HttpServlet {
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
		
		int campaignId = Integer.parseInt(request.getParameter("campaignId"));
		String prizeName = request.getParameter("prizeName");
		int prizeQuantity = Integer.parseInt(request.getParameter("prizeQuantity"));
		String prizeDescription = request.getParameter("prizeDescription");
		
		CampaignPrizesBean prizesBean = new CampaignPrizesBean();
		CampaignBean campaign = new CampaignBean();
		campaign.setCampaign_id(campaignId);
		prizesBean.setCampaignBean(campaign);
		prizesBean.setCampaignPrizes_name(prizeName);
		prizesBean.setCampaignPrizes_quantity(prizeQuantity);
		prizesBean.setCampaignPrizes_description(prizeDescription);
		prizesBean.setCreated_at(new Timestamp(System.currentTimeMillis()));
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		CampaignPrizesService service = new CampaignPrizesService(session);
		
		boolean isCreate = service.createCampaignPrize(prizesBean);
		
		if(isCreate) {
			response.sendRedirect(request.getContextPath() +"/ShowAllPrizeServlet.do");
		}else {
			out.write("新增失敗");
		}
		
		out.close();
	}
}
