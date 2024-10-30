package bistro.bean;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity @Table(name = "Users")
public class UsersBean {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Users_id;
	private String users_account;
	private String users_password;
	private Timestamp created_at;

	public UsersBean() {
	}

	public int getUsers_id() {
		return Users_id;
	}

	public void setUsers_id(int users_id) {
		Users_id = users_id;
	}

	public String getUsers_account() {
		return users_account;
	}

	public void setUsers_account(String users_account) {
		this.users_account = users_account;
	}

	public String getUsers_password() {
		return users_password;
	}

	public void setUsers_password(String users_password) {
		this.users_password = users_password;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	


}
