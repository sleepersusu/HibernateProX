package bistro.SupplyServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import darren.javabean.CampaignPrizesBean;
import darren.javabean.SupplyBean;
import darren.service.CampaignPrizesDaoImpl;
import darren.service.SupplyDaoImpl;


@WebServlet("/UpdateSupplyServlet.do")
public class UpdateSupplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}


	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter();

	    String supplyIdStr = request.getParameter("supplyId");
	    String supplyOri_id = request.getParameter("supplyOri_id");
	    String supplyProduct = request.getParameter("supply_product");
	    String supplyCountStr = request.getParameter("supply_count");
	    String supplyPriceStr = request.getParameter("supply_price");
	    String employeeIdStr = request.getParameter("employee_id");

	    if (supplyIdStr == null || supplyIdStr.trim().isEmpty() ||
	        supplyOri_id == null || supplyOri_id.trim().isEmpty() ||
	        supplyProduct == null || supplyProduct.trim().isEmpty() ||
	        supplyCountStr == null || supplyCountStr.trim().isEmpty() ||
	        supplyPriceStr == null || supplyPriceStr.trim().isEmpty() ||
	        employeeIdStr == null || employeeIdStr.trim().isEmpty()) {
	        out.println("Missing required parameters.");
	        return;
	    }

	    try {
	        int supplyId = Integer.parseInt(supplyIdStr.trim());
	        int supplyOriId = Integer.parseInt(supplyOri_id.trim());
	        int supplyCount = Integer.parseInt(supplyCountStr.trim());
	        int supplyPrice = Integer.parseInt(supplyPriceStr.trim());
	        int employeeId = Integer.parseInt(employeeIdStr.trim());

	        SupplyDaoImpl newDaoImple = new SupplyDaoImpl();
	        SupplyBean bean = new SupplyBean();
	        bean.setSupplyId(supplyId);  // 確保設置supplyId
	        bean.setSupplyOri_id(supplyOriId);
	        bean.setSupplyProduct(supplyProduct);
	        bean.setSupplyCount(supplyCount);
	        bean.setSupplyPrice(supplyPrice);
	        bean.setEmployeeId(employeeId);

	        boolean supply = newDaoImple.update(bean);

	        if (supply) {
	        	 response.sendRedirect(request.getContextPath() + "/ShowAllReadSupplyServlet.do");
	            //out.write("success");
	            return;
	        } else {
	            out.println("Creation failed in DAO.");
	        }
	    } catch (NumberFormatException e) {
	        out.println("Invalid input: supply count, supply price, and employee ID must be numbers.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        out.println("Database error.");
	    } finally {
	        out.close();
	    }
	}
}