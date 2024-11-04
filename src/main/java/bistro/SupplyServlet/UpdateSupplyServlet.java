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
import bistro.service.EmployeeService;
import bistro.service.SupplyOriService;
import bistro.service.SupplyService;
import bistro.util.HibernateUtil;

@WebServlet("/UpdateSupplyServlet.do")
public class UpdateSupplyServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("GET method is working.");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session session = null;
        try {
            // 獲取參數
            String supplyIdParam = request.getParameter("supplyId");
            String supplyOriIdParam = request.getParameter("supplyOriId");
            String supplyProduct = request.getParameter("supplyProduct");
            String supplyCountParam = request.getParameter("supplyCount");
            String supplyPriceParam = request.getParameter("supplyPrice");
            String employeeIdParam = request.getParameter("employeeId");
            String createdAt = request.getParameter("createdAt");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    		LocalDateTime localDateTime = LocalDateTime.parse(createdAt, formatter);
    		Timestamp timestamp = Timestamp.valueOf(localDateTime);
    		
    		
            // 檢查是否所有必要參數都已填寫
            if (supplyIdParam == null || supplyOriIdParam == null || supplyProduct == null ||
                supplyCountParam == null || supplyPriceParam == null || employeeIdParam == null||timestamp==null) {
                request.setAttribute("errorMessage", "確保Ok");
                request.getRequestDispatcher("/ShowAllReadSupplyServlet.do").forward(request, response);
                return;
            }

            // 解析參數
            int supplyId = Integer.parseInt(supplyIdParam);
            int supplyOriId = Integer.parseInt(supplyOriIdParam);
            String supplyProductName = supplyProduct;
            int supplyCount = Integer.parseInt(supplyCountParam);
            int supplyPrice = Integer.parseInt(supplyPriceParam);
            int employeeId = Integer.parseInt(employeeIdParam);
            
            
            
            System.out.println("supplyId: " + supplyIdParam);
            System.out.println("supplyOriId: " + supplyOriIdParam);
            System.out.println("supplyProduct: " + supplyProduct);
            System.out.println("supplyCount: " + supplyCountParam);
            System.out.println("supplyPrice: " + supplyPriceParam);
            System.out.println("employeeId: " + employeeIdParam);
            

            // 開啟 Hibernate session
            SessionFactory factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            session.beginTransaction();

            SupplyService service = new SupplyService(session);
            EmployeeService employeeService = new EmployeeService(session);
            SupplyOriService supplyOriService = new SupplyOriService(session);

            // 創建並填充 SupplyBean
            SupplyBean supplyBean = new SupplyBean();
            supplyBean.setSupplyId(supplyId);
            supplyBean.setSupplyOriBean(supplyOriService.findSupplyOriById(supplyOriId)); // 確保這不會是 null
            supplyBean.setSupplyProduct(supplyProductName);
            supplyBean.setSupplyCount(supplyCount);
            supplyBean.setSupplyPrice(supplyPrice);
            supplyBean.setEmployeeBean(employeeService.findEmployeeById(employeeId)); // 確保這不會是 null
            supplyBean.setCreatedAt(timestamp);
            
            // 更新進貨資料
            boolean isUpdate = service.updateSupply(supplyBean);

            if (isUpdate) {
                session.getTransaction().commit();
                response.sendRedirect(request.getContextPath() + "/ShowAllReadSupplyServlet.do");
            } else {
                session.getTransaction().rollback();
                request.setAttribute("errorMessage", "fail");
                request.getRequestDispatcher("/ShowAllReadSupplyServlet.do").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "check");
            if (session != null && session.getTransaction().isActive()) session.getTransaction().rollback();
            request.getRequestDispatcher("/ShowAllReadSupplyServlet.do").forward(request, response);
        } catch (Exception e) {
            if (session != null && session.getTransaction().isActive()) session.getTransaction().rollback();
            request.setAttribute("errorMessage", "fail");
            e.printStackTrace(); // check
            request.getRequestDispatcher("/ShowAllReadSupplyServlet.do").forward(request, response);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
