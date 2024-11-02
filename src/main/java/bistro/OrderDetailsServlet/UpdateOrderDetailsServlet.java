package bistro.OrderDetailsServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.bean.OrderDetailsBean;
import bistro.service.MenuService;
import bistro.service.OrderDetailsService;
import bistro.util.HibernateUtil;

/**
 * Servlet implementation class UpdateOrderDetailsServlet
 */
@WebServlet(name = "UpdateOrderDetailsServlet.do", urlPatterns = { "/UpdateOrderDetailsServlet.do" })
public class UpdateOrderDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processAction(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processAction(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ClassNotFoundException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		int orderDetailsId = Integer.parseInt(request.getParameter("orderDetailsid"));
		
		
		int orders = Integer.parseInt(request.getParameter("orders"));
		int menuId = Integer.parseInt(request.getParameter("productName"));

		int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
		int totalQuantity = Integer.parseInt(request.getParameter("totalQuantity"));
		int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
		String specialRequest = request.getParameter("specialRequest");

		String createdAt = request.getParameter("created_At");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		LocalDateTime localDateTime = LocalDateTime.parse(createdAt, formatter);
		Timestamp timestamp = Timestamp.valueOf(localDateTime);

		OrderDetailsBean updateBean = new OrderDetailsBean();
		
		updateBean.setOrderDetails_id(orderDetailsId);
		updateBean.getOrders().setOrdersId(orders);
		updateBean.getProduct().setMenuid(menuId);
		updateBean.setProduct_quantity(productQuantity);
		updateBean.setTotal_quantity(totalQuantity);
		updateBean.setTotal_price(totalPrice);
		updateBean.setSpecial_requests(specialRequest);
		updateBean.setCreated_at(timestamp);
	
		

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		OrderDetailsService service = new OrderDetailsService(session);

		boolean isUpdate = service.updateOrderDetails(updateBean);

		if (isUpdate) {
			response.sendRedirect("ShowAllOrderDetailsServlet.do");
		} else {
			out.write("編輯失敗");
		}

	}

}
