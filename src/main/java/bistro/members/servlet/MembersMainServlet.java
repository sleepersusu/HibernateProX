package bistro.members.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.bean.MembersBean;
import bistro.service.MembersService;
import bistro.util.HibernateUtil;

@WebServlet("/MembersMainServlet.do")
public class MembersMainServlet extends HttpServlet {
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
		
		MembersService mbService = new MembersService(session);
		
		List<MembersBean> allMembers = mbService.findAllMembers();
		
		request.setAttribute("allMembers", allMembers);
		
		request.getRequestDispatcher("MembersMain.jsp").forward(request, response);
		
	}

}
