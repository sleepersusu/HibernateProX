package bistro.SupplyServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import darren.javabean.CampaignBean;
import darren.javabean.SupplyBean;
import darren.service.CampaignDaoImpl;
import darren.service.SupplyDaoImpl;


@WebServlet("/DeleteSupplyServlet.do")
public class DeleteSupplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processAction(request, response);
		} catch (ClassNotFoundException | IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processAction(request, response);
		} catch (ClassNotFoundException | IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void processAction(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException, ServletException {
		
		String supplyIdStr = request.getParameter("Supply_id");

	    if (supplyIdStr == null || supplyIdStr.trim().isEmpty()) {
	        request.setAttribute("errorMessage", "缺少供應品 ID，無法刪除。");
	        request.getRequestDispatcher("/ShowAllReadSupplyServlet.do").forward(request, response);
	        return;
	    }

	    try {
	        int supplyId = Integer.parseInt(supplyIdStr);
	        SupplyDaoImpl daoImpl = new SupplyDaoImpl();
	        
	        // 根據 ID 查找供應品
	        SupplyBean supplyById = daoImpl.findSupplyById(supplyId);

	        if (supplyById == null || supplyById.getSupplyId() == null) {
	            // 如果找不到該供應品或者 ID 為 null，返回錯誤信息
	            request.setAttribute("errorMessage", "找不到對應的供應品 ID，無法刪除。");
	            request.getRequestDispatcher("/ShowAllReadSupplyServlet.do").forward(request, response);
	            return;
	        }

	        // 刪除操作
	        boolean isDelete = daoImpl.deleteSupply(supplyById);
	        if (isDelete) {
	            response.sendRedirect(request.getContextPath() + "/ShowAllReadSupplyServlet.do");
	        } else {
	            request.setAttribute("errorMessage", "刪除失敗。");
	            request.getRequestDispatcher("/ShowAllReadSupplyServlet.do").forward(request, response);
	        }

	    } catch (NumberFormatException e) {
	        // 當供應品 ID 不是有效整數時
	        request.setAttribute("errorMessage", "供應品 ID 無效。");
	        request.getRequestDispatcher("/ShowAllReadSupplyServlet.do").forward(request, response);
	    }
	    
	}
}
