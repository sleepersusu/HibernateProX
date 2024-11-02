package bistro.SupplyServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.bean.SupplyBean;
import bistro.service.SupplyService;
import bistro.util.HibernateUtil;

@WebServlet("/ShowAllReadSupplyServlet.do")
public class ShowAllReadSupplyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取 Hibernate Session
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        
        // 创建服务实例
        SupplyService supplyService = new SupplyService(session);
        
        // 获取所有供货单
        List<SupplyBean> allSupplies = supplyService.findAllSupplies();
        
        // 将供货单信息设置到请求属性中
        request.setAttribute("allSupplies", allSupplies);
        
        // 转发到 JSP 页面以显示供货单
        request.getRequestDispatcher("SupplyAll.jsp").forward(request, response);
    }
}
