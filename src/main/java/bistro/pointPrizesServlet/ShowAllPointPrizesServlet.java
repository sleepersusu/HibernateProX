package bistro.pointPrizesServlet;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.bean.PointPrizesBean;
import bistro.service.PointPrizesService;
import bistro.util.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ShowAllPointPrizesServlet.do")
public class ShowAllPointPrizesServlet extends HttpServlet{
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
		
		PointPrizesService service = new PointPrizesService(session);
		List<PointPrizesBean> allPointPrizes = service.findAllPointPrizes();
		
		request.setAttribute("allPointPrizes", allPointPrizes);
		
		request.getRequestDispatcher("ShowAllPointPrizes.jsp").forward(request, response);
		
	}
	
	
}