package bistro.bean;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;


@Entity
@Table(name = "Menu")
public class MenuBean {

	@Id
	@Column(name = "MENU_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int menuid;

	@Column(name = "PRODUCT_CATEGORY")
	private String productCategory;

	@Column(name = "PRODUCT_IMAGE")
	private byte[] productImage;


	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "PRODUCT_PRICE")
	private int productPrice;
	
	@Column(name = "product_description")
	private String productDescription;

public int getMenuid() {
		return menuid;
	}

	public void setMenuid(int menuid) {
		this.menuid = menuid;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public byte[] getProductImage() {
		return productImage;
	}

	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}





	

//	

}
