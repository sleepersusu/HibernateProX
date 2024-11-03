package bistro.service;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import bistro.bean.Reservation;
import bistro.dao.ReservationDao;

public class ReservationService {

	
	private ReservationDao dao;
	
	public ReservationService(Session session) {
		dao = new ReservationDao(session);
	}
	
	public Reservation queryById(int reservationId) {
		return dao.query(reservationId);
	}
	
	public List<Reservation> queryAll(){
		
		List<Reservation> reservationList = dao.queryALL();
		
		for (Reservation reservation : reservationList) {
			System.out.println(reservation.getSeatsId()+" "+reservation.getReservationId());
		}
		
		return reservationList;
	}
	
	public boolean insert(Reservation r) {
		return dao.insert(r);
	}
	
	public boolean deleteById(Reservation r) {
		return dao.delete(r);
	}
	
	public boolean update(Reservation r) {
		return dao.update(r);
	}
}
