package bistro.SupplyServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.bean.CampaignPrizesBean;
import bistro.bean.EmployeeBean;
import bistro.bean.SupplyBean;
import bistro.bean.SupplyOriBean;
import bistro.service.SupplyService;
import bistro.util.HibernateUtil;


@WebServlet("/CreateSupplyServlet.do")
public class CreateSupplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = null;
        SupplyService service = null;

        try {
            session = factory.openSession();
            service = new SupplyService(session);
            session.beginTransaction();

            SupplyBean supply = new SupplyBean();
            String supplyProduct = request.getParameter("supply_product");
            String supplyCountStr = request.getParameter("supply_count");
            String supplyPriceStr = request.getParameter("supply_price");
            String supplyOriIdStr = request.getParameter("supplyOri_id");
            String employeeIdStr = request.getParameter("employee_id");

            // 参数检查
            if (supplyProduct == null || supplyProduct.isEmpty()) {
                throw new IllegalArgumentException("供应产品不能为空");
            }
            if (supplyCountStr == null || supplyCountStr.isEmpty()) {
                throw new IllegalArgumentException("供应数量不能为空");
            }
            if (supplyPriceStr == null || supplyPriceStr.isEmpty()) {
                throw new IllegalArgumentException("供应价格不能为空");
            }
            if (supplyOriIdStr == null || supplyOriIdStr.isEmpty()) {
                throw new IllegalArgumentException("供应商ID不能为空");
            }
            if (employeeIdStr == null || employeeIdStr.isEmpty()) {
                throw new IllegalArgumentException("员工ID不能为空");
            }

            supply.setSupplyProduct(supplyProduct);
            supply.setSupplyCount(Integer.parseInt(supplyCountStr));
            supply.setSupplyPrice(Double.parseDouble(supplyPriceStr));
            supply.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            int supplyOriId = Integer.parseInt(supplyOriIdStr);
            SupplyOriBean supplyOri = session.get(SupplyOriBean.class, supplyOriId);
            if (supplyOri != null) {
                supply.getSupplyOriBean().add(supplyOri);
            } else {
                throw new Exception("供应商未找到");
            }

            int employeeId = Integer.parseInt(employeeIdStr);
            EmployeeBean employee = session.get(EmployeeBean.class, employeeId);
            if (employee != null) {
                supply.getEmployeeBean().add(employee);
            } else {
                throw new Exception("员工未找到");
            }

            service.createSupply(supply);
            session.getTransaction().commit();
            response.sendRedirect("ShowAllReadSupplyServlet.do");
        } catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "创建供应信息失败: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}