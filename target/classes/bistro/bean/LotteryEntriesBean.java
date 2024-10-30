package bistro.bean;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "Lottery_entries")
public class LotteryEntriesBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Lottery_entries_id;
    private Timestamp created_at;
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id")
    private CampaignBean campaign; // 
    
    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderDetailsBean orderDetailsBean;
    
    public LotteryEntriesBean() {
    }
	public OrderDetailsBean getOrderDetailsBean() {
		return orderDetailsBean;
	}


	public void setOrderDetailsBean(OrderDetailsBean orderDetailsBean) {
		this.orderDetailsBean = orderDetailsBean;
	}

	
    
    public int getLottery_entries_id() {
        return Lottery_entries_id;
    }

    public void setLottery_entries_id(int Lottery_entries_id) {
        this.Lottery_entries_id = Lottery_entries_id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public CampaignBean getCampaign() {
        return campaign;
    }

    public void setCampaign(CampaignBean campaign) {
        this.campaign = campaign;
    }
    
    @Override
    public String toString() {
        return "LotteryEntriesBean{" +
                "Lottery_entries_id=" + Lottery_entries_id + ",\n" +
                "created_at=" + created_at + ",\n" +
                "note='" + note + '\'' + ",\n" +
                "campaign=" + (campaign != null ? campaign.getCampaign_id() : "null") + ",\n" +
                "orderDetailsBean=" + (orderDetailsBean != null ? orderDetailsBean.getOrderDetails_id() : "null") +
                '}';
    }

    
}
