package bistro.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import bistro.bean.Reservation;





public class ReservationDao  {
	
	private Session session; 
	
	public ReservationDao(Session session) {
		this.session = session;
	}
	

	public List<Reservation> queryALL() {
		Query<Reservation> query = session.createQuery("from Reservation",Reservation.class);
		return (List<Reservation>) query.list();
	}

	
	public Boolean insert(Reservation r) {
		session.persist(r);
		return true;
	}

	
	public Boolean update(Reservation r) {
		session.merge(r);
		session.flush();
		return true;
	}

	
	public Boolean delete(Reservation r) {
		session.remove(r);
		return true;
	}


	public Reservation query(Integer reservationId) {				
		return session.get(Reservation.class, reservationId);
	}

}
