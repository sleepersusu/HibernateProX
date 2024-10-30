package bistro.bean;



import java.sql.Timestamp;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;



@Entity
@Table(name="OrderDetails")
public class OrderDetailsBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="OrderDetails_id")
	private int orderDetails_id; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orders_id",referencedColumnName = "Orders_id")
	private OrdersBean orders; //訂單編號 FK ordersID ( Orders.Orders_id )
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id",referencedColumnName = "Menu_id")
	private MenuBean product; //商品編號 FK productID ( Menu. Menu_id)
	
	
	private int product_quantity; //單件商品數量
	
	private int total_quantity;
	
	private int total_price; // 單件商品數量*單件商品價格 
	
	private String special_requests; //特殊要求
	
	private Timestamp created_at;

	public int getOrderDetails_id() {
		return orderDetails_id;
	}

	public void setOrderDetails_id(int orderDetails_id) {
		this.orderDetails_id = orderDetails_id;
	}

	public OrdersBean getOrders() {
		return orders;
	}

	public void setOrders(OrdersBean orders) {
		this.orders = orders;
	}

	public MenuBean getProduct() {
		return product;
	}

	public void setProduct(MenuBean product) {
		this.product = product;
	}

	public int getProduct_quantity() {
		return product_quantity;
	}

	public void setProduct_quantity(int product_quantity) {
		this.product_quantity = product_quantity;
	}

	public int getTotal_quantity() {
		return total_quantity;
	}

	public void setTotal_quantity(int total_quantity) {
		this.total_quantity = total_quantity;
	}

	public int getTotal_price() {
		return total_price;
	}

	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}

	public String getSpecial_requests() {
		return special_requests;
	}

	public void setSpecial_requests(String special_requests) {
		this.special_requests = special_requests;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	
	

	

	
	
	
}
