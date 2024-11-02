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
        //  Hibernate Session
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        
        // 創建實例
        SupplyService supplyService = new SupplyService(session);
        
        // get all 所有供貨單
        List<SupplyBean> allSupplies = supplyService.findAllSupplies();
        
        //將供貨單訊息填入
        request.setAttribute("allSupplies", allSupplies);
        
        
        request.getRequestDispatcher("SupplyAll.jsp").forward(request, response);
    }
}
