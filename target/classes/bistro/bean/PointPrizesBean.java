package bistro.bean;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "PointPrizes")
public class PointPrizesBean{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PointPrizes_id")
	private int PointPrizes_id;
	
	@Column(name = "PointPrizes_name")
	private String pointPrizes_name;
	
	@Column(name = "pointPrizes_points")
	private int pointPrizes_points;
	
	@Column(name = "pointPrizes_description")
	private String pointPrizes_description;
	
    @Column(name = "pointPrizes_expiration")
    @Temporal(TemporalType.DATE)
	private Date pointPrizes_expiration;

	public int getPointPrizes_id() {
		return PointPrizes_id;
	}

	public void setPointPrizes_id(int pointPrizes_id) {
		PointPrizes_id = pointPrizes_id;
	}

	public String getPointPrizes_name() {
		return pointPrizes_name;
	}

	public void setPointPrizes_name(String pointPrizes_name) {
		this.pointPrizes_name = pointPrizes_name;
	}

	public int getPointPrizes_points() {
		return pointPrizes_points;
	}

	public void setPointPrizes_points(int pointPrizes_points) {
		this.pointPrizes_points = pointPrizes_points;
	}

	public String getPointPrizes_description() {
		return pointPrizes_description;
	}

	public void setPointPrizes_description(String pointPrizes_description) {
		this.pointPrizes_description = pointPrizes_description;
	}

	public Date getPointPrizes_expiration() {
		return pointPrizes_expiration;
	}

	public void setPointPrizes_expiration(Date pointPrizes_expiration) {
		this.pointPrizes_expiration = pointPrizes_expiration;
	}
    

}
