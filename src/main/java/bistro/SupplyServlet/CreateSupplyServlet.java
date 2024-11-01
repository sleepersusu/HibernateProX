package bistro.SupplyServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;

import bistro.bean.CampaignPrizesBean;
import bistro.bean.SupplyBean;


@WebServlet("/CreateSupplyServlet.do")
public class CreateSupplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processAction(request, response);
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processAction(request, response);
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException, SQLException {
	
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String supplyOri_id = request.getParameter("supplyOri_id");
	    String supplyProduct = request.getParameter("supply_product");
	    String supplyCountStr = request.getParameter("supply_count");
	    String supplyPriceStr = request.getParameter("supply_price");
	    String employeeIdStr = request.getParameter("employee_id");

	    // Debug: Print all received parameters to check their values
	    System.out.println("Received Parameters:");
	    System.out.println("supply_name: " + supplyOri_id);
	    System.out.println("supply_product: " + supplyProduct);
	    System.out.println("supply_count: " + supplyCountStr);
	    System.out.println("supply_price: " + supplyPriceStr);
	    System.out.println("employee_id: " + employeeIdStr);

	    // Check if parameters are non-null and non-empty
	    if (supplyOri_id == null || supplyOri_id.trim().isEmpty() ||
	        supplyProduct == null || supplyProduct.trim().isEmpty() ||
	        supplyCountStr == null || supplyCountStr.trim().isEmpty() ||
	        supplyPriceStr == null || supplyPriceStr.trim().isEmpty() ||
	        employeeIdStr == null || employeeIdStr.trim().isEmpty()) {
	        
	       // out.println("Missing required parameters.");
	        return;
	    }

	    try {
	    	int supplyOriId = Integer.parseInt(supplyOri_id);
	        int supplyCount = Integer.parseInt(supplyCountStr);
	        int supplyPrice = Integer.parseInt(supplyPriceStr);
	        int employeeId = Integer.parseInt(employeeIdStr);
	        
	        SupplyDaoImpl newDaoImple = new SupplyDaoImpl();
	        SupplyBean bean = new SupplyBean();
	        bean.setSupplyOri_id(supplyOriId);
	        bean.setSupplyProduct(supplyProduct);
	        bean.setSupplyCount(supplyCount);
	        bean.setSupplyPrice(supplyPrice);
	        bean.setEmployeeId(employeeId);
	        
	        boolean supply = newDaoImple.create(bean);
	        
	        if (supply) {
	            // Ensure that the response is redirected and no further output is executed
	            response.sendRedirect(request.getContextPath() + "/ShowAllReadSupplyServlet.do");
	        	//out.write("sucess");
	            return; // Make sure the method exits after redirection
	        } else {
	            out.println("Creation failed.");
	        }
	        
	    } catch (NumberFormatException e) {
	        out.println("Invalid input: supply count, supply price, and employee ID must be numbers.");
	    } finally {
	        out.close(); // Close the PrintWriter only after all operations are complete
	    }

	}}