package bistro.bean;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "SupplyOri")
public class SupplyOriBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SupplyOri_id")
    private int supplyOriId;

    @Column(name = "supplyOri_name")
    private String supplyOriName;

    @Column(name = "supplyOri_tel")
    private String supplyOriTel;

    @Column(name = "supplyOri_address")
    private String supplyOriAddress;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @ManyToMany(mappedBy = "supplyOriBeans", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SupplyBean> supplyBeans = new HashSet<>();

    
    public SupplyOriBean() {}

    
    // Getters and Setters...


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


	public Set<SupplyBean> getSupplyBeans() {
		return supplyBeans;
	}


	public void setSupplyBeans(Set<SupplyBean> supplyBeans) {
		this.supplyBeans = supplyBeans;
	};
        
    
    
}
