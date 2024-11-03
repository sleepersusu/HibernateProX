package bistro.bean;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity @Table(name = "MembersDetail")
public class MembersDetailBean {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer MembersDetail_id;
//	@Column(name = "members_id", insertable = false, updatable = false)
//	private Integer members_id; 
	private byte[] membersD_img;
	private String membersD_name;
	private Integer membersD_age;
	private Short membersD_sex;
	private Date membersD_birthday;
	private Short membersD_favor;
	private String membersD_address;
	private String membersD_phone;
	private String membersD_email;
	
	//測試新增前台需求屬性
	@Transient
    private String userSexStr;  // 性別字串
	@Transient
    private String userFavorStr;  // 偏好字串
	
	@OneToOne
    @JoinColumn(name = "members_id") // 外键
	private MembersBean MembersBean;
	
	public MembersDetailBean() {
	}


	public MembersDetailBean(byte[] membersD_img, String membersD_name, Integer membersD_age,
			Short membersD_sex, Date membersD_birthday, Short membersD_favor, String membersD_address,
			String membersD_phone, String membersD_email, bistro.bean.MembersBean membersBean) {
		super();
		this.membersD_img = membersD_img;
		this.membersD_name = membersD_name;
		this.membersD_age = membersD_age;
		this.membersD_sex = membersD_sex;
		this.membersD_birthday = membersD_birthday;
		this.membersD_favor = membersD_favor;
		this.membersD_address = membersD_address;
		this.membersD_phone = membersD_phone;
		this.membersD_email = membersD_email;
		MembersBean = membersBean;
	}


	public MembersDetailBean(byte[] membersD_img, String membersD_name, Integer membersD_age, Short membersD_sex,
			Date membersD_birthday, Short membersD_favor, String membersD_address, String membersD_phone,
			String membersD_email) {
		super();
		this.membersD_img = membersD_img;
		this.membersD_name = membersD_name;
		this.membersD_age = membersD_age;
		this.membersD_sex = membersD_sex;
		this.membersD_birthday = membersD_birthday;
		this.membersD_favor = membersD_favor;
		this.membersD_address = membersD_address;
		this.membersD_phone = membersD_phone;
		this.membersD_email = membersD_email;
	}


	public Integer getMembersDetail_id() {
		return MembersDetail_id;
	}


	public void setMembersDetail_id(Integer membersDetail_id) {
		MembersDetail_id = membersDetail_id;
	}


	public byte[] getMembersD_img() {
		return membersD_img;
	}


	public void setMembersD_img(byte[] membersD_img) {
		this.membersD_img = membersD_img;
	}


	public String getMembersD_name() {
		return membersD_name;
	}


	public void setMembersD_name(String membersD_name) {
		this.membersD_name = membersD_name;
	}


	public Integer getMembersD_age() {
		return membersD_age;
	}


	public void setMembersD_age(Integer membersD_age) {
		this.membersD_age = membersD_age;
	}


	public Short getMembersD_sex() {
		return membersD_sex;
	}


	public void setMembersD_sex(Short membersD_sex) {
		this.membersD_sex = membersD_sex;
	}


	public Date getMembersD_birthday() {
		return membersD_birthday;
	}


	public void setMembersD_birthday(Date membersD_birthday) {
		this.membersD_birthday = membersD_birthday;
	}


	public Short getMembersD_favor() {
		return membersD_favor;
	}


	public void setMembersD_favor(Short membersD_favor) {
		this.membersD_favor = membersD_favor;
	}


	public String getMembersD_address() {
		return membersD_address;
	}


	public void setMembersD_address(String membersD_address) {
		this.membersD_address = membersD_address;
	}


	public String getMembersD_phone() {
		return membersD_phone;
	}


	public void setMembersD_phone(String membersD_phone) {
		this.membersD_phone = membersD_phone;
	}


	public String getMembersD_email() {
		return membersD_email;
	}


	public void setMembersD_email(String membersD_email) {
		this.membersD_email = membersD_email;
	}


	public MembersBean getMembersBean() {
		return MembersBean;
	}


	public void setMembersBean(MembersBean membersBean) {
		MembersBean = membersBean;
	}




	public String getUserSexStr() {
		return userSexStr;
	}




	public void setUserSexStr(String userSexStr) {
		this.userSexStr = userSexStr;
	}




	public String getUserFavorStr() {
		return userFavorStr;
	}




	public void setUserFavorStr(String userFavorStr) {
		this.userFavorStr = userFavorStr;
	}



}
