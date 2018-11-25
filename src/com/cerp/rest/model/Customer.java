package com.cerp.rest.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3528659731178388397L;

	private int custId;
	
	private String custfname;
	private String custlname;
	private String email;
	private String phone;
	private String purchasedate;
	private String cust_refno;
	private String address;
	private String tokenid;
	private String status;
	private String auserid;
	
	private Productdetails prodinfo;
	private ArrayList<Productdetails> productinfo;
	private CustomerDB dbinfo;
	private CustomerLoginDetails loginfo;
	
	public Customer(){
		
	}
	
	public ArrayList<Productdetails> getProductinfo() {
		return productinfo;
	}

	public Productdetails getProdinfo() {
		return prodinfo;
	}

	public void setProdinfo(Productdetails prodinfo) {
		this.prodinfo = prodinfo;
	}

	public void setProductinfo(ArrayList<Productdetails> productinfo) {
		this.productinfo = productinfo;
	}

	public CustomerDB getDbinfo() {
		return dbinfo;
	}

	public void setDbinfo(CustomerDB dbinfo) {
		this.dbinfo = dbinfo;
	}

	public CustomerLoginDetails getLoginfo() {
		return loginfo;
	}

	public void setLoginfo(CustomerLoginDetails loginfo) {
		this.loginfo = loginfo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAuserid() {
		return auserid;
	}

	public void setAuserid(String auserid) {
		this.auserid = auserid;
	}

	


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	public String getCust_refno() {
		return cust_refno;
	}

	public void setCust_refno(String cust_refno) {
		this.cust_refno = cust_refno;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int result) {
		this.custId = result;
	}

	public String getCustfname() {
		return custfname;
	}

	public void setCustfname(String custfname) {
		this.custfname = custfname;
	}

	public String getCustlname() {
		return custlname;
	}

	public void setCustlname(String custlname) {
		this.custlname = custlname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPurchasedate() {
		return purchasedate;
	}

	public void setPurchasedate(String purchasedate) {
		this.purchasedate = purchasedate;
	}

	public String getTokenid() {
		return tokenid;
	}

	public void setTokenid(String tokenid) {
		this.tokenid = tokenid;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custfname=" + custfname + ", custlname=" + custlname + ", email="
				+ email + ", phone=" + phone + ", purchasedate=" + purchasedate + ", cust_refno=" + cust_refno
				+ ", address=" + address + ", tokenid=" + tokenid + ", status=" + status + ", auserid=" + auserid
				+ ", prodinfo=" + prodinfo + ", productinfo=" + productinfo + ", dbinfo=" + dbinfo + ", loginfo="
				+ loginfo + "]";
	}
	
	
}
