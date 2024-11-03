package bistro.SupplyServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import bistro.service.SupplyService;
import bistro.util.HibernateUtil;
import bistro.bean.SupplyBean;


@WebServlet("/DeleteSupplyServlet.do")
public class DeleteSupplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        int id = Integer.parseInt(request.getParameter("supplyId"));

	        SessionFactory factory = HibernateUtil.getSessionFactory();
	        Session session = factory.getCurrentSession();
	        SupplyService service = new SupplyService(session);
	        SupplyBean supply = service.findSupplyById(id);
	        if (supply != null) {
	            service.deleteSupply(supply);
	        }

	        response.sendRedirect("ShowAllReadSupplyServlet.do");
	    }
	}