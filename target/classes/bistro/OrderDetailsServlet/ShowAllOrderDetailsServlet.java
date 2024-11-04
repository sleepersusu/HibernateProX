package bistro.OrderDetailsServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import bistro.bean.OrderDetailsBean;
import bistro.dao.OrderDetailsDao;
import bistro.util.HibernateUtil;

/**
 * Servlet implementation class ShowAllOrderDetailsServlet
 */
@WebServlet(name = "ShowAllOrderDetailsServlet.do", urlPatterns = { "/ShowAllOrderDetailsServlet.do" })
public class ShowAllOrderDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);

	}


	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");

		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		OrderDetailsDao orderDetailsDao = new OrderDetailsDao(session);
		List<OrderDetailsBean> allOrderDetails = orderDetailsDao.queryAllOrderDetails();
		
		
		request.setAttribute("allOrderDetails", allOrderDetails);
		
		request.getRequestDispatcher("ShowAllOrderDetails.jsp").forward(request, response);

		
	}

}
