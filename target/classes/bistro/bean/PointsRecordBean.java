package bistro.bean;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="PointsRecords")
public class PointsRecordBean {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PointsRecords_id")
	private int PointsRecords_id;
	
    @ManyToOne
    @JoinColumn(name = "members_id", referencedColumnName = "members_id")
    private MembersDetailBean membersDetail;
	
	@OneToOne
	@JoinColumn(name = "prize_id")
	private PointPrizesBean PointPrizes;
	
    @Column(name = "records_date")
    @Temporal(TemporalType.DATE)
	private Date records_date;

	public int getPointsRecords_id() {
		return PointsRecords_id;
	}

	public void setPointsRecords_id(int pointsRecords_id) {
		PointsRecords_id = pointsRecords_id;
	}

	public MembersDetailBean getMembersDetail() {
		return membersDetail;
	}

	public void setMembersDetail(MembersDetailBean membersDetail) {
		this.membersDetail = membersDetail;
	}

	public PointPrizesBean getPointPrizes() {
		return PointPrizes;
	}

	public void setPointPrizes(PointPrizesBean pointPrizes) {
		PointPrizes = pointPrizes;
	}

	public Date getRecords_date() {
		return records_date;
	}

	public void setRecords_date(Date records_date) {
		this.records_date = records_date;
	}


}
