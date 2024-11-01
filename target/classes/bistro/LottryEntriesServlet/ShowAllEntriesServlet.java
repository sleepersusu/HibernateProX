package bistro.LottryEntriesServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.bean.LotteryEntriesBean;
import bistro.service.LotteryEntriesService;
import bistro.util.HibernateUtil;

@WebServlet("/ShowAllEntriesServlet.do")
public class ShowAllEntriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);

	}

	private void processAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		LotteryEntriesService service = new LotteryEntriesService(session);
		List<LotteryEntriesBean> allEntries = service.findAllEntries();
		request.setAttribute("allEntries", allEntries);
		request.getRequestDispatcher("ShowAllEntries.jsp").forward(request, response);

	}

}
