package bistro.MenuServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import bistro.bean.MenuBean;
import bistro.service.MenuService;
import bistro.util.HibernateUtil;


@MultipartConfig
@WebServlet(name = "EditMenuServlet.do", urlPatterns = { "/EditMenuServlet.do" })
public class EditMenuServlet extends HttpServlet {
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
		PrintWriter out = response.getWriter();
		
		int id = Integer.parseInt(request.getParameter("menuid"));
		String category = request.getParameter("productCategory");
		String productName = request.getParameter("productName");
		int productPrice = Integer.parseInt(request.getParameter("productPrice"));
		String productDescribe = request.getParameter("productDescription");


		Part filepart = request.getPart("productImage");
		InputStream inputStream = filepart.getInputStream();
		byte[] imgdata = inputStream.readAllBytes();
		
		
		MenuBean menuBean = new MenuBean();
		
		menuBean.setMenuid(id);
		menuBean.setProductCategory(category);
		menuBean.setProductName(productName);
		menuBean.setProductPrice(productPrice);
		menuBean.setProductDescription(productDescribe);
		menuBean.setProductImage(imgdata);
		
		
		

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		MenuService menuService = new MenuService(session);

		boolean isUpdate = menuService.updateMenu(menuBean);

		if (isUpdate) {
			response.sendRedirect(request.getContextPath() + "/ShowAllMenuServlet.do");
		} else {
			out.write("更新失敗...");
		}

	}

}
