package bistro.bean;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Supply")
public class SupplyBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Supply_id")
    private int supplyId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false) // 每个供货只能由一名员工处理
    private EmployeeBean employeeBean;

    @ManyToMany
    @JoinTable(
        name = "SupplyOriMerge",
        joinColumns = @JoinColumn(name = "Supply_id"),
        inverseJoinColumns = @JoinColumn(name = "SupplyOri_id")
    )
    private Set<SupplyOriBean> supplyOriBeans = new HashSet<>();

    @Column(name = "supply_product")
    private String supplyProduct;

    @Column(name = "supply_count")
    private int supplyCount;

    @Column(name = "supply_price")
    private int supplyPrice;

    @Column(name = "created_at")
    private Timestamp createdAt;
    
    
    
    public SupplyBean() {}



	public int getSupplyId() {
		return supplyId;
	}



	public void setSupplyId(int supplyId) {
		this.supplyId = supplyId;
	}



	public EmployeeBean getEmployeeBean() {
		return employeeBean;
	}



	public void setEmployeeBean(EmployeeBean employeeBean) {
		this.employeeBean = employeeBean;
	}



	public Set<SupplyOriBean> getSupplyOriBeans() {
		return supplyOriBeans;
	}



	public void setSupplyOriBeans(Set<SupplyOriBean> supplyOriBeans) {
		this.supplyOriBeans = supplyOriBeans;
	}



	public String getSupplyProduct() {
		return supplyProduct;
	}



	public void setSupplyProduct(String supplyProduct) {
		this.supplyProduct = supplyProduct;
	}



	public int getSupplyCount() {
		return supplyCount;
	}



	public void setSupplyCount(int supplyCount) {
		this.supplyCount = supplyCount;
	}



	public int getSupplyPrice() {
		return supplyPrice;
	}



	public void setSupplyPrice(int supplyPrice) {
		this.supplyPrice = supplyPrice;
	}



	public Timestamp getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	};

    // Getters and Setters...
    
    
    
    
    
}
