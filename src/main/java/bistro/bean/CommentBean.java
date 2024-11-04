package bistro.bean;

import java.sql.Timestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Comment")
public class CommentBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Comment_id;
	
	
	@OneToOne
	@JoinColumn(name="comment_order",referencedColumnName = "OrderDetails_id")
	private OrderDetailsBean comment_order;
	
	
	@ManyToOne
	@JoinColumn(name="product_id",referencedColumnName = "Menu_id")
	private MenuBean product_id;
	
	private int rating;
	
	private String comment_message;
	
	private Timestamp comment_date;

	public int getComment_id() {
		return Comment_id;
	}

	public void setComment_id(int comment_id) {
		Comment_id = comment_id;
	}

	public OrderDetailsBean getComment_order() {
		return comment_order;
	}

	public void setComment_order(OrderDetailsBean comment_order) {
		this.comment_order = comment_order;
	}

	public MenuBean getProduct_id() {
		return product_id;
	}

	public void setProduct_id(MenuBean product_id) {
		this.product_id = product_id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment_message() {
		return comment_message;
	}

	public void setComment_message(String comment_message) {
		this.comment_message = comment_message;
	}

	public Timestamp getComment_date() {
		return comment_date;
	}

	public void setComment_date(Timestamp comment_date) {
		this.comment_date = comment_date;
	}
	
	
}
