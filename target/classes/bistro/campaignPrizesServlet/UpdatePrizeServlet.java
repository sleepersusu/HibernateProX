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




@WebServlet("/UpdatePrizeServlet.do")
public class UpdatePrizeServlet extends HttpServlet {
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
		int campaignId = Integer.parseInt(request.getParameter("campaignId"));
		String prizeName = request.getParameter("prizeName");
		int prizeQuantity = Integer.parseInt(request.getParameter("prizeQuantity"));
		String prizeDescription = request.getParameter("prizeDescription");
		Timestamp createAt = Timestamp.valueOf(request.getParameter("createAt"));
		
		CampaignPrizesBean bean = new CampaignPrizesBean();
		
		bean.setCampaignPrizes_id(prizeId);
		CampaignBean campaignBean = new CampaignBean();
		campaignBean.setCampaign_id(campaignId);
		bean.setCampaignBean(campaignBean);
		bean.setCampaignPrizes_name(prizeName);
		bean.setCampaignPrizes_quantity(prizeQuantity);
		bean.setCampaignPrizes_description(prizeDescription);
		bean.setCreated_at(createAt);
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		CampaignPrizesService service = new CampaignPrizesService(session);
		
		boolean isUpdate = service.updateCampaignPrize(bean);
		
		if(isUpdate) {
			response.sendRedirect(request.getContextPath() + "/ShowAllPrizeServlet.do");
		}else {
			out.write("更新獎品資訊失敗...");
		}
		
	}

}
