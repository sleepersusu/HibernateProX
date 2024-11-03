package bistro.MenuServlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import bistro.bean.MenuBean;
import bistro.service.MenuService;
import bistro.util.HibernateUtil;

/**
 * Servlet implementation class DeleteMenuByIdServlet
 */
@WebServlet(name = "DeleteMenuByIdServlet.do", urlPatterns = { "/DeleteMenuByIdServlet.do" })
public class DeleteMenuByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}


	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		int menuid = Integer.parseInt(request.getParameter("menuid"));
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		MenuService service = new MenuService(session);
		MenuBean queryMenuByID = service.queryMenuByID(menuid);
		
		boolean isDelete = service.deleteMenu(queryMenuByID);
				
		if(isDelete) {
			response.sendRedirect(request.getContextPath() + "/ShowAllMenuServlet.do");
		}else {
			out.println("刪除失敗...");
		}
		
		out.close();
		
	}
	

}
