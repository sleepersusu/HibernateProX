package bistro.ReservationServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.bean.Reservation;
import bistro.dao.ReservationDao;
import bistro.service.ReservationService;
import bistro.util.HibernateUtil;




@WebServlet("/ReservationUpdateServlet.do")
public class ReservationUpdateServlet extends HttpServlet {
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
		Reservation r = new Reservation();
		int reservationsId=Integer.parseInt(request.getParameter("reservationId"));
		String customerName=request.getParameter("customerName");
		Boolean customerGender=Boolean.parseBoolean("customerGender");
		String contactPhone=request.getParameter("contactPhone");
		String Str = request.getParameter("reservationDateTime");
//	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;	    
	    System.out.println(Str);	    
	        try {
	        	
	            LocalDateTime dateTime = LocalDateTime.parse(Str, formatter);
	            
	            Timestamp timestamp = Timestamp.valueOf(dateTime);
	           
	            r.setReservationDateTime(timestamp);
	            System.out.println("AAA"+r.getReservationDateTime());
	        } catch (DateTimeParseException e) {
	            // 處理解析失敗的情況
	           
	        	response.sendError(HttpServletResponse.SC_BAD_REQUEST, "日期時間格式錯誤");
	        }
	        
	    int numberPeople=Integer.parseInt(request.getParameter("numberPeople"));
	    int seatsId=Integer.parseInt(request.getParameter("seatsId"));
	    String reservationStatus=request.getParameter("reservationStatus");
	    String notes=request.getParameter("notes");
	    
	    r.setReservationId(reservationsId);
	    r.setCustomerName(customerName);
	    r.setCustomerGender(customerGender);
	    r.setContactPhone(contactPhone);
	    r.setNumberPeople(numberPeople);
	    r.setSeatsId(seatsId);
	    r.setReservationStatus(reservationStatus);
	    r.setNotes(notes);
	    
	    SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		ReservationService service = new  ReservationService(session);
	    
		boolean isUpdate = service.update(r);	    

		if(isUpdate) {
			response.sendRedirect(request.getContextPath() + "/ReservationAllqueryServlet.do");
		}else {
			out.write("更新資訊失敗...");
		}
		
	}

}
