package bistro.loginServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.bean.UsersBean;
import bistro.service.UserService;
import bistro.util.HibernateUtil;



@WebServlet("/RegisterServlet.do")
public class RegisterServlet extends HttpServlet {
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

		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");

		UsersBean bean = new UsersBean();
		bean.setUsers_account(userName);
		bean.setUsers_password(userPassword);
		bean.setCreated_at(new Timestamp(System.currentTimeMillis()));
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		UserService service = new UserService(session);
		boolean isCreate = service.createUser(bean);

		if (isCreate) {
			out.println("<script type='text/javascript'>");
			out.println("alert('註冊成功!');");
			out.println("window.location.href = '" + request.getContextPath() + "/login.jsp';");
			out.println("</script>");
		} else {
			out.println("<script type='text/javascript'>");
			out.println("alert('註冊失敗!');");
			out.println("window.history.back();"); 
			out.println("</script>");
		}

	}

}
