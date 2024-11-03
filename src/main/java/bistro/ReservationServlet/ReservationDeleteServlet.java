package bistro.ReservationServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import bistro.bean.Reservation;
import bistro.service.ReservationService;
import bistro.util.HibernateUtil;

@WebServlet("/ReservationDeleteServlet.do")
public class ReservationDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");

		int reservationId = Integer.parseInt(request.getParameter("reservationsId"));
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		ReservationService service = new ReservationService(session);
		Reservation delete = service.queryById(reservationId);

		boolean isDelete = service.deleteById(delete);
		System.out.println(isDelete);
		if (isDelete) {
			response.sendRedirect(request.getContextPath() + "/ReservationAllqueryServlet.do");
		} else {
			request.setAttribute("errorMessage", "刪除訂位失敗");
			request.getRequestDispatcher("/ReservationAllqueryServlet.do").forward(request, response);
		}



	}

}
