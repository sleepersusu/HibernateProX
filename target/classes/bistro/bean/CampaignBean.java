package bistro.bean;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity @Table(name = "Campaign")
public class CampaignBean {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int campaign_id;
	private String campaign_title;
	private String campaign_description;
	private String campaign_type;
	private Timestamp campaign_start_date;
	private Timestamp end_date;
	private Timestamp created_at;
	private String note;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "campaignBean", cascade = CascadeType.ALL)
	private List<CampaignPrizesBean> campaignPrizes = new LinkedList<CampaignPrizesBean>();
	

	public List<CampaignPrizesBean> getCampaignBeans() {
		return campaignPrizes;
	}

	public void setCampaignBeans(List<CampaignPrizesBean> campaignPrizes) {
		this.campaignPrizes = campaignPrizes;
	}	

	public CampaignBean() {}

    public int getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(int campaign_id) {
        this.campaign_id = campaign_id;
    }

    public String getCampaign_title() {
        return campaign_title;
    }

    public void setCampaign_title(String campaign_title) {
        this.campaign_title = campaign_title;
    }

    public String getCampaign_description() {
        return campaign_description;
    }

    public void setCampaign_description(String campaign_description) {
        this.campaign_description = campaign_description;
    }

    public Timestamp getCampaign_start_date() {
        return campaign_start_date;
    }

    public void setCampaign_start_date(String date) throws ParseException {
        this.campaign_start_date = parseDate(date);
    }

    public Timestamp getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String date) throws ParseException {
        this.end_date = parseDate(date);
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

    public String getCampaign_type() {
        return campaign_type;
    }

    public void setCampaign_type(String campaign_type) {
        this.campaign_type = campaign_type;
    }

    private Timestamp parseDate(String date) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            return new Timestamp(sdf1.parse(date).getTime());
        } catch (ParseException e1) {
            try {
                return new Timestamp(sdf2.parse(date).getTime());
            } catch (ParseException e2) {
                return new Timestamp(sdf3.parse(date).getTime());
            }
        }
    }




    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("campaignId : ").append(this.getCampaign_id()).append("\n")
          .append("campaignTitle : ").append(this.getCampaign_title()).append("\n")
          .append("campaignDescription : ").append(this.getCampaign_description()).append("\n")
          .append("campaignType : ").append(this.getCampaign_type()).append("\n")
          .append("campaignStartDate : ").append(this.getCampaign_start_date()).append("\n")
          .append("campaignEndDate : ").append(this.getEnd_date()).append("\n")
          .append("createdAt : ").append(this.getCreated_at()).append("\n")
          .append("note : ").append(this.getNote()).append("\n")
          .append("campaignPrizes : ");

        if (campaignPrizes != null && !campaignPrizes.isEmpty()) {
            for (CampaignPrizesBean prize : campaignPrizes) {
                sb.append("\n  - ").append(prize.toString());
            }
        } else {
            sb.append("No prizes available");
        }

        sb.append("\n------------------------------------------------");
        return sb.toString();
    }


}
