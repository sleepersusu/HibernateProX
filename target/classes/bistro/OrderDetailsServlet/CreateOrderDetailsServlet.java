package bistro.OrderDetailsServlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.bean.MenuBean;
import bistro.bean.OrderDetailsBean;
import bistro.bean.OrdersBean;
import bistro.service.OrderDetailsService;
import bistro.util.HibernateUtil;

@MultipartConfig
@WebServlet(name = "CreateOrderDetailsServlet.do", urlPatterns = { "/CreateOrderDetailsServlet.do" })
public class CreateOrderDetailsServlet extends HttpServlet {
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
		
		
		int orders = Integer.parseInt(request.getParameter("orders"));
		
		int productid = Integer.parseInt(request.getParameter("productid"));
		String productName = request.getParameter("productName");

		Integer productPrice =Integer.parseInt( request.getParameter("productPrice"));
		Integer productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
		Integer totaltQuantity = Integer.parseInt(request.getParameter("totalQuantity"));
		Integer totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
//		String customerName = request.getParameter("customerName");
//		Integer seat = Integer.parseInt(request.getParameter("seat"));
//		String orderStatus = request.getParameter("orderStatus");
		String specialRequest = request.getParameter("specialRequest");
		String createdAt = request.getParameter("createdAt");
	
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(createdAt, formatter);
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
		

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		OrderDetailsService service = new OrderDetailsService(session);
		
		
		OrderDetailsBean bean = new OrderDetailsBean();
		
		OrdersBean ordersBean = new OrdersBean();
		ordersBean.setOrdersId(orders);
//		ordersBean.setSeatId();
//		ordersBean.setCustomerName();
		
		MenuBean menuBean = new MenuBean();
		menuBean.setProductName(productName);
		menuBean.setProductPrice(productPrice);
		menuBean.setMenuid(productid);
		
		bean.setOrders(ordersBean);
		bean.setProduct(menuBean);
		
		
		bean.getOrders().getOrdersId();
		bean.setProduct_quantity(productQuantity);
		bean.setTotal_quantity (totaltQuantity);
		bean.setTotal_price(totalPrice);
//		bean.getOrders().setCustomerName(customerName);
//		bean.getOrders().setSeatId(seat);
//		bean.getOrders().setOrderStatus(orderStatus);
		bean.setSpecial_requests(specialRequest);
		bean.setCreated_at(timestamp);
		
		 boolean isCreate = service.createOrderDetails(bean);
		 
		 if (isCreate) {
				response.sendRedirect(request.getContextPath() + "/ShowAllOrderDetailsServlet.do");

			} else {
				out.println("新增失敗...");
			}
	}

}
