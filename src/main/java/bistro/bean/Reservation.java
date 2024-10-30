package bistro.bean;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity @Table(name="Reservations")
public class Reservation {
	
//	private LinkedList<Reservation> lists = new LinkedList<Reservation>(); //好像不需要
	
	
	@Id @Column(name="reservations_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reserveationId;
	
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Seats_id")
    private Seat seatsId;
	
	
	
	@Column(name="members_id")
	private Integer memberId;
	
	@Column(name="customer_name")
	private String customerName;
	
	@Column(name="customer_gender")
	private Boolean customerGender;
	
	@Column(name="contact_phone")
	private String contactPhone;
	
	@Column(name="reservation_time")
	private Timestamp reservationDateTime;
	
	@Column(name="number_people")
	private Integer numberPeople;
	
	@Column(name="reservation_status")
	private String reservationStatus = "已確認"; // 預設值;
	
	@Column(name="notes")
	private String notes;
	
	@Column(name="created_at")
	private Timestamp createdAt;

	
	public Reservation() {
		// TODO Auto-generated constructor stub
	}


	public Integer getReserveationId() {
		return reserveationId;
	}


	public void setReserveationId(Integer reserveationId) {
		this.reserveationId = reserveationId;
	}


	public Integer getMemberId() {
		return memberId;
	}


	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public Boolean getCustomerGender() {
		return customerGender;
	}


	public void setCustomerGender(Boolean customerGender) {
		this.customerGender = customerGender;
	}


	public String getContactPhone() {
		return contactPhone;
	}


	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}


	public Timestamp getReservationDateTime() {
		return reservationDateTime;
	}


	public void setReservationDateTime(Timestamp reservationDateTime) {
		this.reservationDateTime = reservationDateTime;
	}


	public Integer getNumberPeople() {
		return numberPeople;
	}


	public void setNumberPeople(Integer numberPeople) {
		this.numberPeople = numberPeople;
	}


	public String getReservationStatus() {
		return reservationStatus;
	}


	public void setReservationStatus(String reservationStatus) {
		this.reservationStatus = reservationStatus;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public Timestamp getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}


	
	



}
