package bistro.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "Supply")
public class SupplyBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Supply_id")
    private int supplyId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeBean employeeBean; // 不使用 Cascade

    @ManyToOne
    @JoinColumn(name = "supplyOri_id", nullable = false)
    private SupplyOriBean supplyOriBean; // 不使用 Cascade

    @Column(name = "supply_product", nullable = false)
    private String supplyProduct;

    @Column(name = "supply_count", nullable = false)
    private int supplyCount;

    @Column(name = "supply_price", nullable = false)
    private int supplyPrice;

    @Column(name = "created_at")
    private Timestamp createdAt;

    
    public SupplyBean() {}
    
    
    // Getters and Setters
    // ...
    


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


	public SupplyOriBean getSupplyOriBean() {
		return supplyOriBean;
	}


	public void setSupplyOriBean(SupplyOriBean supplyOriBean) {
		this.supplyOriBean = supplyOriBean;
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
    
    // Getters and Setters
    // ...
    
    
    
    
    
    
    
    
    
    
}
