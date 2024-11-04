package bistro.SupplyServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.bean.SupplyBean;
import bistro.bean.EmployeeBean; // 引入 EmployeeBean
import bistro.bean.SupplyOriBean; // 引入 SupplyOriBean
import bistro.service.SupplyService;
import bistro.service.EmployeeService; // 引入 EmployeeService
import bistro.service.SupplyOriService; // 引入 SupplyOriService
import bistro.util.HibernateUtil;

@WebServlet("/CreateSupplyServlet.do")
public class CreateSupplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SupplyBean supply = new SupplyBean();

		// 請求獲取參數
		int supplyOriId = Integer.parseInt(request.getParameter("supplyOriId"));
		String supplyProduct = request.getParameter("supplyProduct");
		int supplyCount = Integer.parseInt(request.getParameter("supplyCount"));
		int supplyPrice = Integer.parseInt(request.getParameter("supplyPrice"));
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		String createdAt = request.getParameter("createdAt");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		LocalDateTime localDateTime = LocalDateTime.parse(createdAt, formatter);
		Timestamp timestamp = Timestamp.valueOf(localDateTime);

		// 設定 SupplyBean 的属性
		supply.setSupplyProduct(supplyProduct);
		supply.setSupplyCount(supplyCount);
		supply.setSupplyPrice(supplyPrice);
		supply.setCreatedAt(new Timestamp(System.currentTimeMillis()));

		// 獲取 Hibernate Session
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		// 創建物件接住
		SupplyService supplyService = new SupplyService(session);
		EmployeeService employeeService = new EmployeeService(session);
		SupplyOriService supplyOriService = new SupplyOriService(session);

		// 查找相關的 EmployeeBean 和 SupplyOriBean
		EmployeeBean employee = employeeService.findEmployeeById(employeeId);
		SupplyOriBean supplyOri = supplyOriService.findSupplyOriById(supplyOriId);

		// 設定關聯對象
		supply.setEmployeeBean(employee);
		supply.setSupplyOriBean(supplyOri);
		supply.setCreatedAt(timestamp);

		// 調用方法保存
		supplyService.createSupply(supply);

		// 回到首頁servlet.do
		response.sendRedirect("ShowAllReadSupplyServlet.do");
	}
}
