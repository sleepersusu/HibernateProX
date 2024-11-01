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




@Entity @Table(name = "Supply")
public class SupplyBean {
	
		
		@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		private int Supply_id;
		
		
		// hibernate可以透過關聯存取資訊
		@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)//2邊都要寫
		@JoinTable(name = "SupplyOriMerge", joinColumns = {@JoinColumn(name = "Supply_id")}, inverseJoinColumns = {@JoinColumn(name="SupplyOri_id")}) //FK
		private List<SupplyOriBean> supplyOriBean = new LinkedList<SupplyOriBean>();
		
		
		@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		@JoinTable(name = "SupplyEmployeeMerge", joinColumns = {@JoinColumn(name = "Supply_id")}, inverseJoinColumns = {@JoinColumn(name="Employee_id")}) //FK
		private List<EmployeeBean> employeeBean = new LinkedList<EmployeeBean>();
	
		
		
		
		private String supply_product;
		private int supply_count;
		private int supply_price;
		private Timestamp created_at;
		
		
		
		public SupplyBean() {}



		public int getSupply_id() {
			return Supply_id;
		}



		public void setSupply_id(int supply_id) {
			Supply_id = supply_id;
		}



		public List<SupplyOriBean> getSupplyOriBean() {
			return supplyOriBean;
		}



		public void setSupplyOriBean(List<SupplyOriBean> supplyOriBean) {
			this.supplyOriBean = supplyOriBean;
		}



		public List<EmployeeBean> getEmployeeBean() {
			return employeeBean;
		}



		public void setEmployeeBean(List<EmployeeBean> employeeBean) {
			this.employeeBean = employeeBean;
		}



		public String getSupply_product() {
			return supply_product;
		}



		public void setSupply_product(String supply_product) {
			this.supply_product = supply_product;
		}



		public int getSupply_count() {
			return supply_count;
		}



		public void setSupply_count(int supply_count) {
			this.supply_count = supply_count;
		}



		public int getSupply_price() {
			return supply_price;
		}



		public void setSupply_price(int supply_price) {
			this.supply_price = supply_price;
		}



		public Timestamp getCreated_at() {
			return created_at;
		}



		public void setCreated_at(Timestamp created_at) {
			this.created_at = created_at;
		}



		public SupplyBean getSupplyBean() {
			// TODO Auto-generated method stub
			return null;
		}
		
		
		
		
		
		
		
		

}
