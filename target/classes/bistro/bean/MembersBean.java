package bistro.bean;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity @Table(name = "Members")
public class MembersBean {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Members_id;
	private String member_account;
	private String member_password;
	
	@OneToOne(mappedBy = "MembersBean", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private MembersDetailBean MembersDetailBean;
	
	public MembersBean() {
	}

	public MembersBean(String member_account, String member_password) {
		this.member_account = member_account;
		this.member_password = member_password;
	}

	public MembersBean(Integer members_id, String member_account, String member_password) {
		Members_id = members_id;
		this.member_account = member_account;
		this.member_password = member_password;
	}

	public Integer getMembers_id() {
		return Members_id;
	}

	public void setMembers_id(Integer members_id) {
		Members_id = members_id;
	}

	public String getMember_account() {
		return member_account;
	}

	public void setMember_account(String member_account) {
		this.member_account = member_account;
	}

	public String getMember_password() {
		return member_password;
	}

	public void setMember_password(String member_password) {
		this.member_password = member_password;
	}

	public MembersDetailBean getMembersDetailBean() {
		return MembersDetailBean;
	}

	public void setMembersDetailBean(MembersDetailBean membersDetailBean) {
		MembersDetailBean = membersDetailBean;
	}
	
}
