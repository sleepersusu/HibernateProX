package bistro.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity @Table(name="Seats")
public class Seat {

	@Id @Column(name="seats_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer seatsId;
	
	@Column(name="seat_number")
	private String seatNumber;
	
	@Column(name="seat_type")
	private String seatType;
	
	
	
	public Seat() {
		
	}



	public Integer getSeatsId() {
		return seatsId;
	}



	public void setSeatsId(Integer seatsId) {
		this.seatsId = seatsId;
	}



	public String getSeatNumber() {
		return seatNumber;
	}



	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}



	public String getSeatType() {
		return seatType;
	}



	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	
	
}
