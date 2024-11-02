package bistro.bean;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import jakarta.persistence.Table;

@Entity
@Table(name = "Orders")
public class OrdersBean {

	@Id
	@Column(name = "Orders_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int ordersId;

	@Column(name = "members_id")
	private Integer membersId;

	@Column(name = "customer_name")
	private String customerName;

	@Column(name = "seats_id")
	private Integer seatId;

	@Column(name = "order_time")
	private Timestamp orderTime;

	@Column(name = "order_status")
	private String orderStatus;

	// 這裡mapped是寫 另一邊的bean
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orders", cascade = CascadeType.ALL)
	private List<OrderDetailsBean> orderdetailsList = new LinkedList<OrderDetailsBean>();

//	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinColumn(name = "seats_id", referencedColumnName = "Seats_id", insertable = false, updatable = false)
//	private Seat seats;
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "members_id", referencedColumnName = "members_id", insertable = false, updatable = false)
//	private MembersDetailBean members;

	public int getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(int ordersId) {
		this.ordersId = ordersId;
	}

	public Integer getMembersId() {
		return membersId;
	}

	public void setMembersId(Integer membersId) {
		this.membersId = membersId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getSeatId() {
		return seatId;
	}

	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<OrderDetailsBean> getOrderdetailsList() {
		return orderdetailsList;
	}

	public void setOrderdetailsList(LinkedList<OrderDetailsBean> orderdetailsList) {
		this.orderdetailsList = orderdetailsList;
	}

	public OrdersBean() {
		
	}



}
