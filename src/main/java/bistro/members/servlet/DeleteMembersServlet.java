package bistro.members.servlet;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.bean.MembersBean;
import bistro.service.MembersService;
import bistro.util.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteMembersServlet.do")
public class DeleteMembersServlet extends HttpServlet {
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
		System.out.println("id:"+request.getParameter("userid"));
		int userid = Integer.parseInt(request.getParameter("userid"));
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		MembersService mbService = new MembersService(session);
		boolean isDelete = mbService.deleteMembers(userid);
		if (isDelete) {
			response.sendRedirect(request.getContextPath() + "/MembersMainServlet.do");
		} else {
			request.setAttribute("errorMessage", "刪除活動失敗");
			request.getRequestDispatcher("/MembersMainServlet.do").forward(request, response);
		}

	}

}
