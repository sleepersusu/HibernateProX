package bistro.LottryEntriesServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.service.LotteryEntriesService;
import bistro.util.HibernateUtil;

@WebServlet("/DeleteEntryServlet.do")
public class DeleteEntryServlet extends HttpServlet {
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
			throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		int enrtyId = Integer.parseInt(request.getParameter("entryId"));
		

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		LotteryEntriesService service = new LotteryEntriesService(session);
		boolean isDelete = service.deleteLotteryEntryById(enrtyId);
		
		if (isDelete) {
			response.sendRedirect(request.getContextPath() + "/ShowAllEntriesServlet.do");
		} else {
			request.setAttribute("errorMessage", "刪除參與者失敗");
			request.getRequestDispatcher("/ShowAllEntriesServlet.do").forward(request, response);
		}

	}

}
