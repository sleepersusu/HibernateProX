package bistro.bean;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Employee")
public class EmployeeBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Employee_id")
    private int employeeId;
    
    
    @OneToOne // 使用 OneToOne 表示一对一关系
    @JoinColumn(name = "users_id", nullable = false) // 外键列
    private UsersBean user;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "employee_gender")
    private String employeeGender;

    @Column(name = "employee_born")
    private Timestamp employeeBorn;

    @Column(name = "employee_tel")
    private String employeeTel;

    @Column(name = "employee_seniority")
    private int employeeSeniority;

    @Column(name = "employee_salary")
    private int employeeSalary;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToMany(mappedBy = "employeeBean", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SupplyBean> supplyBeans = new HashSet<>();
    
    public EmployeeBean() {}
    
    // Getters and Setters...


	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public UsersBean getUser() {
		return user;
	}

	public void setUser(UsersBean user) {
		this.user = user;
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

	public int getEmployeeSeniority() {
		return employeeSeniority;
	}

	public void setEmployeeSeniority(int employeeSeniority) {
		this.employeeSeniority = employeeSeniority;
	}

	public int getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(int employeeSalary) {
		this.employeeSalary = employeeSalary;
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
	}
    

    // Getters and Setters...
    
    
    
    
}
