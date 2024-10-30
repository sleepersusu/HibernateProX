package bistro.bean;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity 
@Table(name = "SupplyOri")
public class SupplyOriBean {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int SupplyOri_id;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)//2邊都要寫
	@JoinTable(name = "SupplyOriMerge", joinColumns = {@JoinColumn(name = "SupplyOri_id")}, inverseJoinColumns = {@JoinColumn(name="Supply_id")}) //FK
	private List<SupplyBean> supplyBean = new LinkedList<SupplyBean>();
	
	
	private String supplyOri_name;
	private String supplyOri_tel;
	
	
	private String supplyOri_address;
	private Timestamp created_at;
	
	public SupplyOriBean() {}

	public int getSupplyOri_id() {
		return SupplyOri_id;
	}

	public void setSupplyOri_id(int supplyOri_id) {
		SupplyOri_id = supplyOri_id;
	}

	public List<SupplyBean> getSupplyBean() {
		return supplyBean;
	}

	public void setSupplyBean(List<SupplyBean> supplyBean) {
		this.supplyBean = supplyBean;
	}

	public String getSupplyOri_name() {
		return supplyOri_name;
	}

	public void setSupplyOri_name(String supplyOri_name) {
		this.supplyOri_name = supplyOri_name;
	}

	public String getSupplyOri_tel() {
		return supplyOri_tel;
	}

	public void setSupplyOri_tel(String supplyOri_tel) {
		this.supplyOri_tel = supplyOri_tel;
	}

	public String getSupplyOri_address() {
		return supplyOri_address;
	}

	public void setSupplyOri_address(String supplyOri_address) {
		this.supplyOri_address = supplyOri_address;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	
	
}
