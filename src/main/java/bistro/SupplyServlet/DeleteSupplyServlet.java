package bistro.SupplyServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import bistro.service.SupplyService;
import bistro.util.HibernateUtil;
import bistro.bean.SupplyBean;


@WebServlet("/DeleteSupplyServlet.do")
public class DeleteSupplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            SupplyService supplyService = new SupplyService(session);
            SupplyBean supply = supplyService.findSupplyById(Integer.parseInt(request.getParameter("supply_id")));
            if (supply != null) {
                supplyService.deleteSupply(supply);
                transaction.commit();
            }
            response.sendRedirect("ShowAllSupplyServlet.do");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}