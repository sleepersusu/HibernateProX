package bistro.OrderDetailsServlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import bistro.bean.OrderDetailsBean;
import bistro.service.OrderDetailsService;
import bistro.util.HibernateUtil;

/**
 * Servlet implementation class DeleteOrderDetailsServlet
 */
@WebServlet(name = "DeleteOrderDetailsServlet.do", urlPatterns = { "/DeleteOrderDetailsServlet.do" })
public class DeleteOrderDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		int orderDetails_id =Integer.parseInt(request.getParameter("orderDetails_id"));
	

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		OrderDetailsService service = new OrderDetailsService(session);
		
		OrderDetailsBean deleteBean = service.queryOrderDetailsByID(orderDetails_id);
		boolean isDelete = service.deleteOrderDetailsById(deleteBean);
		
		if(isDelete) {
			response.sendRedirect(request.getContextPath()+"/ShowAllOrderDetailsServlet.do");
		}else {
			out.println("刪除失敗...");
		}
		out.close();
	}

}
