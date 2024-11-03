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
import java.time.temporal.ChronoUnit;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.bean.Reservation;
import bistro.dao.ReservationDao;
import bistro.service.CampaignService;
import bistro.service.ReservationService;
import bistro.util.HibernateUtil;



@WebServlet("/ReservationInsertServlet.do")
public class ReservationInsertServlet extends HttpServlet {
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
		

		r.setCustomerName(request.getParameter("customerName"));
		r.setCustomerGender(Boolean.parseBoolean(request.getParameter("CustomerGender")));
		r.setContactPhone(request.getParameter("contactPhone"));
		r.setNumberPeople(Integer.parseInt(request.getParameter("numberPeople")));
		
		String dateTimeString = request.getParameter("reservationDateTime");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, formatter);
		Timestamp timestamp = Timestamp.valueOf(localDateTime);
		
		r.setReservationDateTime(timestamp);
	    r.setSeatsId(Integer.parseInt(request.getParameter("seatsId")));	    
	    r.setNotes(request.getParameter("已確認"));
	    String reservationStatus = request.getParameter("reservationStatus");
	    if (reservationStatus == null || reservationStatus.isEmpty()) {
	      reservationStatus = "已確認"; // 預設值
	    }
	    r.setReservationStatus(reservationStatus);
	    
	    SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
	    
		ReservationService service = new ReservationService(session);
	    
	    boolean insert = service.insert(r);
		System.out.println(insert);
		if(insert) {
			response.sendRedirect(request.getContextPath() +"/ReservationAllqueryServlet.do");
		}else {
			out.write("新增失敗");
		}
		
		out.close();
		    
	}
}
