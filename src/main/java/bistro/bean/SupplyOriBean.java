package bistro.bean;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "SupplyOri")
public class SupplyOriBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SupplyOri_id")
    private int supplyOriId;

    @Column(name = "supplyOri_name", nullable = false)
    private String supplyOriName;

    @Column(name = "supplyOri_tel")
    private String supplyOriTel;

    @Column(name = "supplyOri_address")
    private String supplyOriAddress;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToMany(mappedBy = "supplyOriBean", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SupplyBean> supplies;

    public  SupplyOriBean() {}
    
 // Getters and Setters

	public int getSupplyOriId() {
		return supplyOriId;
	}

	public void setSupplyOriId(int supplyOriId) {
		this.supplyOriId = supplyOriId;
	}

	public String getSupplyOriName() {
		return supplyOriName;
	}

	public void setSupplyOriName(String supplyOriName) {
		this.supplyOriName = supplyOriName;
	}

	public String getSupplyOriTel() {
		return supplyOriTel;
	}

	public void setSupplyOriTel(String supplyOriTel) {
		this.supplyOriTel = supplyOriTel;
	}

	public String getSupplyOriAddress() {
		return supplyOriAddress;
	}

	public void setSupplyOriAddress(String supplyOriAddress) {
		this.supplyOriAddress = supplyOriAddress;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public List<SupplyBean> getSupplies() {
		return supplies;
	}

	public void setSupplies(List<SupplyBean> supplies) {
		this.supplies = supplies;
	};
    
    
    
}