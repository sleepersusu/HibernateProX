package bistro.bean;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "Employee")
public class EmployeeBean {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Employee_id;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "SupplyEmployeeMerge", joinColumns = {@JoinColumn(name = "Employee_id")}, inverseJoinColumns = {@JoinColumn(name="Supply_id")}) //FK
	private List<SupplyBean> supplyBean = new LinkedList<SupplyBean>();
	
	@Column(name = "users_id", insertable = false, updatable = false)
	private int users_id;
	
	private String employee_name;
	private String employee_gender;
	private Timestamp employee_born;
	private String employee_tel;
	private int employee_seniority;
	private int employee_salary;
	private Timestamp created_at;
	public int getEmployee_id() {
		return Employee_id;
	}
	public void setEmployee_id(int employee_id) {
		Employee_id = employee_id;
	}
	public List<SupplyBean> getSupplyBean() {
		return supplyBean;
	}
	public void setSupplyBean(List<SupplyBean> supplyBean) {
		this.supplyBean = supplyBean;
	}
	public int getUsers_id() {
		return users_id;
	}
	public void setUsers_id(int users_id) {
		this.users_id = users_id;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getEmployee_gender() {
		return employee_gender;
	}
	public void setEmployee_gender(String employee_gender) {
		this.employee_gender = employee_gender;
	}
	public Timestamp getEmployee_born() {
		return employee_born;
	}
	public void setEmployee_born(Timestamp employee_born) {
		this.employee_born = employee_born;
	}
	public String getEmployee_tel() {
		return employee_tel;
	}
	public void setEmployee_tel(String employee_tel) {
		this.employee_tel = employee_tel;
	}
	public int getEmployee_seniority() {
		return employee_seniority;
	}
	public void setEmployee_seniority(int employee_seniority) {
		this.employee_seniority = employee_seniority;
	}
	public int getEmployee_salary() {
		return employee_salary;
	}
	public void setEmployee_salary(int employee_salary) {
		this.employee_salary = employee_salary;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	
	

}
