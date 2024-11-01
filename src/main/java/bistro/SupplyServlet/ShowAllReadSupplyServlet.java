package bistro.SupplyServlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.service.SupplyService;
import bistro.util.HibernateUtil;
import bistro.bean.SupplyBean;


@WebServlet("/ShowAllReadSupplyServlet.do")
public class ShowAllReadSupplyServlet extends HttpServlet {
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
		
		SupplyService service = new SupplyService(session);
		
		List<SupplyBean> allSupply = service.findAllSupply();
		
		request.setAttribute("allSupply", allSupply);
		
		request.getRequestDispatcher("SupplyAll.jsp").forward(request, response);

		
	}

}
