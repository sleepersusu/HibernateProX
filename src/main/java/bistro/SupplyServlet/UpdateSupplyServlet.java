package bistro.SupplyServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 獲取參數
            String supplyIdParam = request.getParameter("supplyId");
            String supplyOriIdParam = request.getParameter("supplyOriId");
            String supplyProduct = request.getParameter("supplyProduct");
            String supplyCountParam = request.getParameter("supplyCount");
            String supplyPriceParam = request.getParameter("supplyPrice");
            String employeeIdParam = request.getParameter("employeeId");

            // 檢查是否所有必要參數都已填寫
            if (supplyIdParam == null || supplyOriIdParam == null || supplyProduct == null ||
                supplyCountParam == null || supplyPriceParam == null || employeeIdParam == null) {
                request.setAttribute("errorMessage", "请确保所有字段均已填写");
                request.getRequestDispatcher("/ShowAllReadSupplyServlet.do").forward(request, response);
                return;
            }

            // 解析參數
            int supplyId = Integer.parseInt(supplyIdParam);
            int supplyOriId = Integer.parseInt(supplyOriIdParam);
            String supplyProductName = supplyProduct; // 可以直接使用
            int supplyCount = Integer.parseInt(supplyCountParam);
            int supplyPrice = Integer.parseInt(supplyPriceParam);
            int employeeId = Integer.parseInt(employeeIdParam);

            // 開啟 Hibernate session
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.getCurrentSession();

            SupplyService service = new SupplyService(session);
            EmployeeService employeeService = new EmployeeService(session);
            SupplyOriService supplyOriService = new SupplyOriService(session);
            SupplyBean supplyBean = new SupplyBean();
            supplyBean.setSupplyId(supplyId);
            supplyBean.setSupplyOriBean(supplyOriService.findSupplyOriById(supplyOriId)); // 查找供应商
            supplyBean.setSupplyProduct(supplyProductName);
            supplyBean.setSupplyCount(supplyCount);
            supplyBean.setSupplyPrice(supplyPrice);
            supplyBean.setEmployeeBean(employeeService.findEmployeeById(employeeId)); // 查找员工

            // 更新进货单
            boolean isUpdate = service.updateSupply(supplyBean);
            
            if (isUpdate) {
                response.sendRedirect(request.getContextPath() + "/ShowAllReadSupplyServlet.do");
            } else {
                request.setAttribute("errorMessage", "存取失败");
                request.getRequestDispatcher("/ShowAllReadSupplyServlet.do").forward(request, response);
            }
        } catch (NumberFormatException e) {
            // 如果出現解析錯誤，提示用戶
            request.setAttribute("errorMessage", "输入数据格式不正确，请检查！");
            request.getRequestDispatcher("/ShowAllReadSupplyServlet.do").forward(request, response);
        }
    }
}
