package bistro.bean;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Employee")
public class EmployeeBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employeeId;
    
    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private UsersBean usersBean; // 關聯的用戶

    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @Column(name = "employee_gender")
    private String employeeGender;

    @Column(name = "employee_born")
    private Timestamp employeeBorn;

    @Column(name = "employee_tel")
    private String employeeTel;

    @Column(name = "employee_salary")
    private int employeeSalary;

    @Column(name = "employee_seniority")
    private int employeeSeniority;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToMany(mappedBy = "employeeBean", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SupplyBean> supplies;
    
    
    public  EmployeeBean() {}
    
    // Getters and Setters


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public UsersBean getUsersBean() {
		return usersBean;
	}


	public void setUsersBean(UsersBean usersBean) {
		this.usersBean = usersBean;
	}


	public String getEmployeeName() {
		return employeeName;
	}


	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	public String getEmployeeGender() {
		return employeeGender;
	}


	public void setEmployeeGender(String employeeGender) {
		this.employeeGender = employeeGender;
	}


	public Timestamp getEmployeeBorn() {
		return employeeBorn;
	}


	public void setEmployeeBorn(Timestamp employeeBorn) {
		this.employeeBorn = employeeBorn;
	}


	public String getEmployeeTel() {
		return employeeTel;
	}


	public void setEmployeeTel(String employeeTel) {
		this.employeeTel = employeeTel;
	}


	public int getEmployeeSalary() {
		return employeeSalary;
	}


	public void setEmployeeSalary(int employeeSalary) {
		this.employeeSalary = employeeSalary;
	}


	public int getEmployeeSeniority() {
		return employeeSeniority;
	}


	public void setEmployeeSeniority(int employeeSeniority) {
		this.employeeSeniority = employeeSeniority;
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
