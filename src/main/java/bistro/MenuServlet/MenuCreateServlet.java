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
@WebServlet(name = "MenuCreateServlet.do", urlPatterns = { "/MenuCreateServlet.do" })
public class MenuCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			processAction(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			processAction(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String productCategory = request.getParameter("productCategory");
		String productName = request.getParameter("productName");
		String productPrice =request.getParameter("productPrice");
		String productDescribe = request.getParameter("productDescription");


		Part productImage = request.getPart("productImage");
		InputStream inputStream = productImage.getInputStream();
		byte[] imagedata = inputStream.readAllBytes();

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		MenuService menuService = new MenuService(session);
		MenuBean menubean = new MenuBean();

		menubean.setProductCategory(productCategory);
		menubean.setProductName(productName);
		menubean.setProductPrice(Integer.parseInt(productPrice));
		menubean.setProductDescription(productDescribe);
		menubean.setProductImage(imagedata);



		
		
		boolean isMenuCreate = menuService.createMenu(menubean);

		if (isMenuCreate) {
			response.sendRedirect(request.getContextPath() + "/ShowAllMenuServlet.do");

		} else {
			out.println("新增商品失敗...");
		}

	}
}
