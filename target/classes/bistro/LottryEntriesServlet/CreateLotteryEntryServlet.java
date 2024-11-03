package bistro.LottryEntriesServlet;

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
import bistro.bean.LotteryEntriesBean;
import bistro.bean.OrderDetailsBean;
import bistro.bean.OrdersBean;
import bistro.service.LotteryEntriesService;
import bistro.util.HibernateUtil;


@WebServlet("/CreateLotteryEntryServlet.do")
public class CreateLotteryEntryServlet extends HttpServlet {
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
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		String note = request.getParameter("note");
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		LotteryEntriesService service = new LotteryEntriesService(session);
		
		LotteryEntriesBean bean = new LotteryEntriesBean();
		CampaignBean campaignBean = new CampaignBean();
		OrderDetailsBean orderDetailsBean = new OrderDetailsBean();
		OrdersBean ordersBean = new OrdersBean();
		
		campaignBean.setCampaign_id(campaignId);
		ordersBean.setOrdersId(orderId);
		orderDetailsBean.setOrders(ordersBean);

		
		bean.setCampaign(campaignBean);
		bean.setOrderDetailsBean(orderDetailsBean);
		bean.setNote(note);
		bean.setCreated_at(new Timestamp(System.currentTimeMillis()));
		
		boolean isCreate = service.insertLotteryEntry(bean);
		
		if(isCreate) {
			response.sendRedirect(request.getContextPath() + "/ShowAllEntriesServlet.do");			
		}else {
			out.println("新增參與者資訊失敗...");
		}
		
		
		out.close();
		
	}

}
