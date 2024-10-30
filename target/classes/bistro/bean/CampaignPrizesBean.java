package bistro.bean;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "CampaignPrizes")
public class CampaignPrizesBean {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	 	private int CampaignPrizes_id; // 獎品主鍵
	    private String campaignPrizes_name;
	    private int campaignPrizes_quantity; // 獎品數量
	    private String campaignPrizes_description; // 獎品描述
	    private Timestamp created_at; // 建立時間
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "campaign_id")
	    private CampaignBean campaignBean;

		public CampaignPrizesBean() {
		}

		public int getCampaignPrizes_id() {
			return CampaignPrizes_id;
		}

		public void setCampaignPrizes_id(int campaignPrizes_id) {
			CampaignPrizes_id = campaignPrizes_id;
		}

		public String getCampaignPrizes_name() {
			return campaignPrizes_name;
		}

		public void setCampaignPrizes_name(String campaignPrizes_name) {
			this.campaignPrizes_name = campaignPrizes_name;
		}

		public int getCampaignPrizes_quantity() {
			return campaignPrizes_quantity;
		}

		public void setCampaignPrizes_quantity(int campaignPrizes_quantity) {
			this.campaignPrizes_quantity = campaignPrizes_quantity;
		}

		public String getCampaignPrizes_description() {
			return campaignPrizes_description;
		}

		public void setCampaignPrizes_description(String campaignPrizes_description) {
			this.campaignPrizes_description = campaignPrizes_description;
		}

		public Timestamp getCreated_at() {
			return created_at;
		}

		public void setCreated_at(Timestamp created_at) {
			this.created_at = created_at;
		}

		public CampaignBean getCampaignBean() {
			return campaignBean;
		}

		public void setCampaignBean(CampaignBean campaignBean) {
			this.campaignBean = campaignBean;
		}
		
		@Override
		public String toString() {
		    return "CampaignPrizesBean{" +
		            "CampaignPrizes_id=" + CampaignPrizes_id + ",\n" +
		            "campaignPrizes_name='" + campaignPrizes_name + '\'' + ",\n" +
		            "campaignPrizes_quantity=" + campaignPrizes_quantity + ",\n" +
		            "campaignPrizes_description='" + campaignPrizes_description + '\'' + ",\n" +
		            "created_at=" + created_at + ",\n" +
		            "campaignBean=" + (campaignBean != null ? campaignBean.getCampaign_id() : "null") +
		            '}';
		}

		

		

}
